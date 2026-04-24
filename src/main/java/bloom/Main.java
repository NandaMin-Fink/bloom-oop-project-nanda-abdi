import bloom.Garden;
import bloom.Habit;
import bloom.observer.StreakTracker;
import bloom.plant.Plant;
import bloom.plant.PlantFactory;

import java.time.Clock;
import java.time.Duration;


void main() {
    Garden garden = new Garden("Alex");

    Plant rose  = PlantFactory.createPlant("rose",  "Morning Rose");
    Plant mango = PlantFactory.createPlant("mango", "Mango Dream");

    Habit reading  = new Habit("Read 20 minutes",     Duration.ofDays(1), Clock.systemDefaultZone());
    Habit exercise = new Habit("Exercise 30 minutes", Duration.ofDays(1), Clock.systemDefaultZone());

    //plant observes its habit
    reading.addObserver(rose);
    exercise.addObserver(mango);

    // Add streak track
    StreakTracker readingStreak = new StreakTracker();
//    ConsoleLogger readingLog   = new ConsoleLogger();
    reading.addObserver(readingStreak);
//    reading.addObserver(readingLog);

    garden.addHabit(reading);
    garden.addHabit(exercise);

    rose.grow();
    reading.complete();
    mango.wither();

}
