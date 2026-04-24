package bloom.plant;

import bloom.observer.HabitObserver;
import org.slf4j.Logger;

import java.time.Clock;

// Template
public abstract class Plant implements HabitObserver {
    static Logger logger = org.slf4j.LoggerFactory.getLogger(Plant.class);

    public Plant(String name) {
        this(name, Clock.systemUTC());
    }

    public Plant(String name, Clock clock) {
        this.name = name;
        this.currentStage = PlantState.SEEDLING;
        this.clock = clock;
    }

    private String name;
    private PlantState currentStage;
    private final Clock clock;

    // our template function 1
    public final void grow() {
        switch (currentStage) {
            case PlantState.SEEDLING -> {
                logger.info("Growing");
                currentStage = PlantState.GROWING;
            }
            case PlantState.GROWING -> {
                logger.info("Matured!");
                currentStage = PlantState.MATURE;
            }
            case PlantState.MATURE -> {
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
            case PlantState.SEEDLING -> {
                logger.info("Withered away");
                currentStage = PlantState.DEAD;
            }
            case PlantState.GROWING -> {
                logger.info("Regressing");
                currentStage = PlantState.SEEDLING;
            }
            case PlantState.MATURE -> {
                logger.info("Plant lost some growth");
                currentStage = PlantState.GROWING;
            }
            case PlantState.DEAD -> {}
        }

    }

    abstract protected void absorbWater();
    abstract protected void loseNourishment();
    abstract protected void celebrateGrowth();


}
