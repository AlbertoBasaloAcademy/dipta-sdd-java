package academy.aicode.astrobookings.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Launch {
    private UUID id;
    private UUID rocketId;
    private LocalDateTime launchTime;
    private double pricePerTicket;
    private int minimumOccupancy;
    private LaunchStatus status;

    public Launch() {
    }

    public Launch(UUID id, UUID rocketId, LocalDateTime launchTime, double pricePerTicket, int minimumOccupancy, LaunchStatus status) {
        this.id = id;
        this.rocketId = rocketId;
        this.launchTime = launchTime;
        this.pricePerTicket = pricePerTicket;
        this.minimumOccupancy = minimumOccupancy;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRocketId() {
        return rocketId;
    }

    public void setRocketId(UUID rocketId) {
        this.rocketId = rocketId;
    }

    public LocalDateTime getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(LocalDateTime launchTime) {
        this.launchTime = launchTime;
    }

    public double getPricePerTicket() {
        return pricePerTicket;
    }

    public void setPricePerTicket(double pricePerTicket) {
        this.pricePerTicket = pricePerTicket;
    }

    public int getMinimumOccupancy() {
        return minimumOccupancy;
    }

    public void setMinimumOccupancy(int minimumOccupancy) {
        this.minimumOccupancy = minimumOccupancy;
    }

    public LaunchStatus getStatus() {
        return status;
    }

    public void setStatus(LaunchStatus status) {
        this.status = status;
    }
}
