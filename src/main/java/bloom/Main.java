import bloom.Garden;
import bloom.Habit;
import bloom.observer.StreakTracker;
import bloom.plant.Plant;
import bloom.plant.PlantFactory;

import java.time.Clock;
import java.time.Duration;


void main() {
    Garden garden = new Garden("Jack's Garden");

    Plant rose  = PlantFactory.createRosePlant("Rose");
    Plant mango = PlantFactory.createMangoTree("Mango Tree");

    Habit reading  = new Habit("Reading",     Duration.ofDays(1), Clock.systemDefaultZone());
    Habit exercise = new Habit("Wennt for a walk", Duration.ofDays(1), Clock.systemDefaultZone());

    //plant observes its habit
    reading.addObserver(rose);
    exercise.addObserver(mango);

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
