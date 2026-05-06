package academy.aicode.astrobookings.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RocketTest {
    @Test
    public void testRocketCreation() {
        Rocket rocket = new Rocket(null, "Test", 5, RocketRange.Earth);
        assertEquals("Test", rocket.getName());
        assertEquals(5, rocket.getCapacity());
        assertEquals(RocketRange.Earth, rocket.getRange());
        assertFalse(rocket.isDecommissioned());
    }
}
