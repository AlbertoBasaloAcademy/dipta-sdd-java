package academy.aicode.astrobookings.model;

import java.util.UUID;

public class Rocket {
    private UUID id;
    private String name;
    private int capacity;
    private RocketRange range;
    private boolean decommissioned;

    public Rocket() {
    }

    public Rocket(UUID id, String name, int capacity, RocketRange range) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.range = range;
        this.decommissioned = false;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public RocketRange getRange() {
        return range;
    }

    public void setRange(RocketRange range) {
        this.range = range;
    }

    public boolean isDecommissioned() {
        return decommissioned;
    }

    public void setDecommissioned(boolean decommissioned) {
        this.decommissioned = decommissioned;
    }
}
