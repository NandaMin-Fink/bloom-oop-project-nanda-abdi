package bloom.observer;


import bloom.Habit;
import org.slf4j.Logger;

public class StreakTracker implements HabitObserver {
    static Logger logger = org.slf4j.LoggerFactory.getLogger(StreakTracker.class);

    private int currentStreak = 0;

    private int longestStreak = 0;

    @Override
    public void onHabitCompleted(Habit habit) {
        currentStreak++;
        if (currentStreak > longestStreak) {
            longestStreak = currentStreak;
        }
        logger.info("{} has streak: {}", habit.getName(), currentStreak);
    }

    @Override
    public void onHabitNeglected(Habit habit) {
        if (currentStreak > 0) {
            logger.info("{} streak broken it was: {}", habit.getName(), currentStreak);
        }
        currentStreak = 0;
    }

    public int getCurrentStreak() { return currentStreak; }

    public int getLongestStreak() { return longestStreak; }
}