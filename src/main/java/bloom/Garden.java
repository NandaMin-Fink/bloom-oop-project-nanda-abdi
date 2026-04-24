package bloom;

import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Garden {
    static Logger logger = org.slf4j.LoggerFactory.getLogger(Garden.class);

    private final String ownerName;
    private final List<Habit> habits = new ArrayList<>();

    public Garden(String ownerName) {
        this.ownerName = ownerName;
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
        logger.info("Added habit {} to {} garden.", habit.getName(), ownerName);
    }

    public Optional<Habit> findHabit(String habitName) {
        return habits.stream()
                .filter(h -> h.getName().equalsIgnoreCase(habitName))
                .findFirst();
    }

    // We can call on startup or some refresh
    public void checkAllHabits() {
        habits.forEach(Habit::checkAndApplyNeglect);
    }

    public List<Habit> getHabits() {
        return Collections.unmodifiableList(habits);
    }

    public String getOwnerName() {
        return ownerName;
    }
}