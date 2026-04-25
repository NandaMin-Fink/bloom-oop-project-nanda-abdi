package bloom;

import bloom.observer.HabitObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class HabitObserverNotificationTest {

    static class CountingObserver implements HabitObserver {
        int completed = 0;
        int neglected = 0;
        public void onHabitCompleted(Habit h) { completed++; }
        public void onHabitNeglected(Habit h) { neglected++; }
    }

    private Habit habit;
    private CountingObserver obs;

    @BeforeEach
    void setUp() {
        habit = new Habit("A test habit", Duration.ofDays(1), Clock.systemUTC());
        obs = new CountingObserver();
        habit.addObserver(obs);
    }

    @Test
    void testCompletedFires() {
        habit.complete();
        habit.complete();
        assertEquals(2, obs.completed);
    }

    @Test
    void testMissFiresNeglected() {
        habit.miss();
        assertEquals(1, obs.neglected);
    }

    @Test
    void testMultipleObservers() {
        CountingObserver second = new CountingObserver();
        habit.addObserver(second);
        habit.complete();
        assertEquals(1, obs.completed);
        assertEquals(1, second.completed);
        habit.getName(); // coverage
    }
}
