package academy.aicode.astrobookings.service;

import academy.aicode.astrobookings.model.*;
import academy.aicode.astrobookings.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final LaunchService launchService;
    private final RocketService rocketService;

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public BookingService(BookingRepository bookingRepository, LaunchService launchService, RocketService rocketService) {
        this.bookingRepository = bookingRepository;
        this.launchService = launchService;
        this.rocketService = rocketService;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(UUID id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        validateBooking(booking);
        checkLaunchCapacity(booking.getLaunchId());

        booking.setStatus(BookingStatus.created);
        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(UUID id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus(BookingStatus.cancelled);
        return bookingRepository.save(booking);
    }

    private void validateBooking(Booking booking) {
        if (booking.getLaunchId() == null) {
            throw new IllegalArgumentException("Launch ID is required");
        }
        if (booking.getPassengerName() == null || booking.getPassengerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger name is required");
        }
        if (booking.getPassengerEmail() == null || booking.getPassengerEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger email is required");
        }
        if (!EMAIL_PATTERN.matcher(booking.getPassengerEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    private void checkLaunchCapacity(UUID launchId) {
        Launch launch = launchService.getLaunchById(launchId)
                .orElseThrow(() -> new IllegalArgumentException("Launch not found"));
        
        Rocket rocket = rocketService.getRocketById(launch.getRocketId())
                .orElseThrow(() -> new RuntimeException("Rocket not found for this launch"));

        long activeBookingsCount = bookingRepository.findAll().stream()
                .filter(b -> b.getLaunchId().equals(launchId))
                .filter(b -> b.getStatus() == BookingStatus.created)
                .count();

        if (activeBookingsCount >= rocket.getCapacity()) {
            throw new IllegalStateException("Launch is full");
        }
    }
}
