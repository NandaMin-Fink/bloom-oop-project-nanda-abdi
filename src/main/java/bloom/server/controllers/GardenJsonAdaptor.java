package bloom.server.controllers;

import bloom.Garden;
import bloom.plant.PlantState;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class GardenJsonAdaptor {

    public final String owner;
    public final List<JsonHabit> habits = new ArrayList<>();

    public GardenJsonAdaptor(Garden garden, List<HabitBundle> bundles) {

        owner = garden.getOwnerName();
        for (HabitBundle b : bundles) {
            JsonHabit h = new JsonHabit();
            h.habitName = b.habit().getName();
            h.plantType = b.plantType();
            h.plantName = b.plantName();


            h.stage = b.plant().getCurrentStage();
            h.streak = b.streak().getCurrentStreak();
            h.longestStreak = b.streak().getLongestStreak();
            h.neglected = b.habit().isNeglected();
            h.refreshPeriod = b.refreshPeriod();
            h.lastCompleted = b.habit().getLastCompleted();

            habits.add(h);
        }
    }


    public static class JsonHabit {
        public String habitName;
        public String plantType;
        public String plantName;

        public PlantState stage;
        public int streak;

        public int longestStreak;
        public boolean neglected;
        public String refreshPeriod;
        public Instant lastCompleted;
    }
}
