import bloom.Garden;
import bloom.Habit;
import bloom.observer.StreakTracker;
import bloom.plant.Plant;
import bloom.plant.PlantFactory;

import static java.lang.IO.println;

// New in Java 25: Compact source code.
// No explicit class declaration needed.

void main() {
    Garden garden = new Garden("Alex");

    // Factory Pattern: create plants without knowing their concrete types
    Plant rose  = PlantFactory.createPlant("rose",  "Morning Rose");
//    Plant mango = PlantFactory.createPlant("mango", "Mango Dream");

    // Habits use a 1-day refresh period in production
    Habit reading  = new Habit("Read 20 minutes",     Duration.ofDays(1), Clock.systemDefaultZone());
    Habit exercise = new Habit("Exercise 30 minutes", Duration.ofDays(1), Clock.systemDefaultZone());

    // Observer Pattern: Plant observes its own habit (the key join point)
    reading.addObserver(rose);
//    exercise.addObserver(mango);

    // Additional observers injected — Habit never imports these directly
    StreakTracker readingStreak = new StreakTracker();
//    ConsoleLogger readingLog   = new ConsoleLogger();
    reading.addObserver(readingStreak);
//    reading.addObserver(readingLog);

    garden.addHabit(reading);
    garden.addHabit(exercise);

    rose.grow();

}
