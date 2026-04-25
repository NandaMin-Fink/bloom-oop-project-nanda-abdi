package bloom.server.controllers;

import bloom.Habit;
import bloom.observer.StreakTracker;
import bloom.plant.Plant;

// Groups habit together with streak in a nice way for u
public record HabitBundle(Habit habit, Plant plant, StreakTracker streak,
                          String plantType, String plantName, String refreshPeriod) {
}
