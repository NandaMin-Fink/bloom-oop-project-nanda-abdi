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

    public Plant(String name) {
        this(name, Clock.systemUTC());
    }

    public Plant(String name, Clock clock) {
        this.name = name;
        this.currentStage = PlantState.SEEDLING;
        this.clock = clock;
    }


    // our template function 1
    public final void grow() {
        switch (currentStage) {
            case SEEDLING -> {
                logger.info("Growing");
                currentStage = PlantState.GROWING;
                absorbWater();

            }
            case GROWING -> {
                logger.info("Matured!");
                currentStage = PlantState.MATURE;
                celebrateGrowth();
            }
            case MATURE -> {
                logger.info("Continue to maintain your matured plant");
            }
            case DEAD ->  {
                logger.info("Your habit already died");
            }
        }
    }

    // our template function 2
    public final void wither() {
        switch (currentStage) {
            case SEEDLING -> {
                logger.info("Withered away");
                currentStage = PlantState.DEAD;
                loseNourishment();
            }
            case GROWING -> {
                logger.info("Regressing");
                currentStage = PlantState.SEEDLING;
                loseNourishment();
            }
            case MATURE -> {
                logger.info("Plant lost some growth");
                currentStage = PlantState.GROWING;
                loseNourishment();

            }
            case DEAD -> {}
        }

    }
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
