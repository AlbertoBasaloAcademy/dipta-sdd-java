package academy.aicode.astrobookings.model;

import java.util.UUID;

public class Booking {
    private UUID id;
    private UUID launchId;
    private String passengerName;
    private String passengerEmail;
    private String passengerPhone;
    private BookingStatus status;

    public Booking() {
    }

    public Booking(UUID id, UUID launchId, String passengerName, String passengerEmail, String passengerPhone, BookingStatus status) {
        this.id = id;
        this.launchId = launchId;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.passengerPhone = passengerPhone;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getLaunchId() {
        return launchId;
    }

    public void setLaunchId(UUID launchId) {
        this.launchId = launchId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public String getPassengerPhone() {
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
