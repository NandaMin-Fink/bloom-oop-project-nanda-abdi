package bloom.observer;


import bloom.Habit;

/**
 * Concrete Observer — tracks consecutive habit completions.
 */
public class StreakTracker implements HabitObserver {

    private int currentStreak = 0;
    private int longestStreak = 0;

    @Override
    public void onHabitCompleted(Habit habit) {
        currentStreak++;
        if (currentStreak > longestStreak) {
            longestStreak = currentStreak;
        }
        System.out.println("[StreakTracker] \"" + habit.getName() + "\" streak: " + currentStreak);
    }

    @Override
    public void onHabitNeglected(Habit habit) {
        if (currentStreak > 0) {
            System.out.println("[StreakTracker] Streak broken for \"" + habit.getName()
                    + "\". Was: " + currentStreak);
        }
        currentStreak = 0;
    }

    public int getCurrentStreak() { return currentStreak; }
    public int getLongestStreak() { return longestStreak; }
}