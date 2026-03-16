package bloom.observer;


import bloom.Habit;

/**
 * OBSERVER PATTERN — Contract for anything that reacts to habit events.
 * bloom.plant.Plant implements this, as do StreakTracker and ConsoleLogger.
 * bloom.Habit never imports any concrete bloom.observer — they are injected via addObserver().
 */
public interface HabitObserver {
    void onHabitCompleted(Habit habit);
    void onHabitNeglected(Habit habit);
}