package bloom.observer;


import bloom.Habit;
// Observer interface
public interface HabitObserver {
    void onHabitCompleted(Habit habit);
    void onHabitNeglected(Habit habit);
}