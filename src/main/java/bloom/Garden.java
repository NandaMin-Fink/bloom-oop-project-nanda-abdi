package bloom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Garden {

    private final String ownerName;
    private final List<Habit> habits = new ArrayList<>();

    public Garden(String ownerName) {
        this.ownerName = ownerName;
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
        System.out.println("Added habit " + habit.getName() + " to " + ownerName + " garden.");
    }

    public Optional<Habit> findHabit(String habitName) {
        return habits.stream()
                .filter(h -> h.getName().equalsIgnoreCase(habitName))
                .findFirst();
    }

    // Good to call on startup or some refresh that we end up determining
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