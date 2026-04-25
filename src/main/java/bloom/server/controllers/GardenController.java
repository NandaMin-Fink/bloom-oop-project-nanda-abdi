package bloom.server.controllers;

import bloom.Garden;
import bloom.Habit;
import bloom.observer.StreakTracker;
import bloom.plant.Plant;
import bloom.plant.PlantFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class GardenController {

    private final Garden garden = new Garden("Owner"); // need a default for now would be nice to change later not sure what though
    private final Map<String, HabitBundle> bundles = new ConcurrentHashMap<>();
    private final Clock clock = Clock.systemDefaultZone();

    @GetMapping("/api/garden")
    public ResponseEntity<?> getGarden() {
        return new ResponseEntity<>(snapshot(), HttpStatus.OK);
    }

    @PostMapping("/api/garden/habits")
    public ResponseEntity<?> addHabit(@Validated @RequestBody CreateHabitRequest req) {
        if (bundles.containsKey(req.habitName())) {
            return new ResponseEntity<>("Habit already exists!", HttpStatus.CONFLICT);
        }

        Duration period = parseRefresh(req.refreshPeriod());

        String plantType = req.plantType().trim().toLowerCase();
        Plant plant;
        if (plantType.equals("rose")) {
            plant = PlantFactory.createRosePlant(req.plantName(), clock);
        } else if (plantType.equals("apple")) {
            plant = PlantFactory.createAppleTree(req.plantName(), clock);
        } else if (plantType.equals("mango")) {
            plant = PlantFactory.createMangoTree(req.plantName(), clock);
        } else {
            throw new IllegalArgumentException("Unknown plant type");
        }

        Habit habit = new Habit(req.habitName(), period, clock);
        StreakTracker streak = new StreakTracker();

        //we can use our observer pattern here
        habit.addObserver(plant);
        habit.addObserver(streak);

        garden.addHabit(habit);
        bundles.put(habit.getName(),
                new HabitBundle(habit, plant, streak, req.plantType(), req.plantName(), req.refreshPeriod()));

        return new ResponseEntity<>(snapshot(), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/garden/habits/{name}")
    //need this for it to really look goodo therwise they will pile up as they fail todo later
    public ResponseEntity<?> deleteHabit(@PathVariable String name) {
        HabitBundle b = bundles.remove(name);
        if (b == null) {

            return new ResponseEntity<>("Habit not found!", HttpStatus.NOT_FOUND);
        }

        garden.removeHabit(b.habit());
        return new ResponseEntity<>(snapshot(), HttpStatus.OK);
    }

    @PostMapping("/api/garden/habits/{name}/water")
    public ResponseEntity<?> water(@PathVariable String name) {
        HabitBundle b = bundles.get(name);
        if (b == null) {
            return new ResponseEntity<>("Habit not found!", HttpStatus.NOT_FOUND);
        }
        //update if not
        b.habit().complete();

        return new ResponseEntity<>(snapshot(), HttpStatus.OK);
    }

    @PostMapping("/api/garden/habits/{name}/miss")
    // situation when they dont remember their habit (we need to decrease)
    public ResponseEntity<?> miss(@PathVariable String name) {

        HabitBundle b = bundles.get(name);
        if (b == null) {
            return new ResponseEntity<>("Habit not found!", HttpStatus.NOT_FOUND);
        }
        b.habit().miss();

        return new ResponseEntity<>(snapshot(), HttpStatus.OK);
    }

    @PostMapping("/api/garden/refresh")
    public ResponseEntity<?> refresh() {

        garden.checkAllHabits();
        return new ResponseEntity<>(snapshot(), HttpStatus.OK);
    }

    private GardenJsonAdaptor snapshot() {
        return new GardenJsonAdaptor(garden, new ArrayList<>(bundles.values()));
    }

    private Duration parseRefresh(String period) {
        return switch (period.toLowerCase()) {
            case "daily" -> Duration.ofDays(1);
            case "weekly" -> Duration.ofDays(7);
            case "12h" -> Duration.ofHours(12);

            default -> Duration.ofDays(1);
        };
    }
}
