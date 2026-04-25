package bloom.observer;

import bloom.Habit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class StreakTrackerTest {

    private StreakTracker tracker;

    private Habit habit;

    @BeforeEach
    void setUp() {
        tracker = new StreakTracker();
        habit = new Habit("habitT", Duration.ofDays(1), Clock.systemUTC());
    }

    @Test
    void testCompletedAHabit() {
        assertEquals(0, tracker.getCurrentStreak());
        assertEquals(0, tracker.getLongestStreak());

        tracker.onHabitCompleted(habit);
        tracker.onHabitCompleted(habit);
        tracker.onHabitCompleted(habit);
        assertEquals(3, tracker.getCurrentStreak());
    }


    @Test
    void testNeglectWithoutStreak() {
        tracker.onHabitNeglected(habit);
        assertEquals(0, tracker.getCurrentStreak());
    }
}
