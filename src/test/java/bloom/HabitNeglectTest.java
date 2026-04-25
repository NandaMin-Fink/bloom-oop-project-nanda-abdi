package bloom;

import bloom.observer.HabitObserver;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class HabitNeglectTest {

    @Test
    void testNotNeglectedBeforeComplete() {
        Habit h = new Habit("Read", Duration.ofDays(1), Clock.systemUTC());
        assertFalse(h.isNeglected());
        h.getName();
    }

}
