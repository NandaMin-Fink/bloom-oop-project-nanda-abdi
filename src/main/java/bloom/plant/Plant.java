package bloom.plant;

import bloom.Habit;
import bloom.observer.HabitObserver;
import org.slf4j.Logger;

import java.time.Clock;

// Template
public abstract class Plant implements HabitObserver {
    static Logger logger = org.slf4j.LoggerFactory.getLogger(Plant.class);

    private String name;
    private PlantState currentStage;
    private final Clock clock;

    private PlantState seedlingState;
    private PlantState growingState;
    private PlantState matureState;
    private PlantState deadState;

    public Plant(String name) {
        this(name, Clock.systemUTC());
    }

    public Plant(String name, Clock clock) {
        this.name = name;
        this.clock = clock;
    }


    public void setStates(PlantState seedling, PlantState growing, PlantState mature, PlantState dead) {
        this.seedlingState = seedling;
        this.growingState = growing;
        this.matureState = mature;
        this.deadState = dead;
        this.currentStage = this.seedlingState; // Set initial state
    }

    void setStage(PlantState newStage) { this.currentStage = newStage; }
    public PlantState getGrowingState() { return growingState; }
    public PlantState getDeadState() { return deadState; }
    public PlantState getMatureState() { return matureState; }
    public PlantState getSeedlingState() { return seedlingState; }

    public final void grow() { currentStage.handleGrow(this); }
    public final void wither() { currentStage.handleWither(this); }

    @Override
    public void onHabitCompleted(Habit habit) {
        grow();
    }

    @Override
    public void onHabitNeglected(Habit habit) {
        wither();
    }

    public PlantState getCurrentStage() {
        return currentStage;
    }

    abstract protected void absorbWater();
    abstract protected void loseNourishment();
    abstract protected void celebrateGrowth();


}
