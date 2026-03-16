package bloom.plant;

import bloom.observer.HabitObserver;

import java.time.Clock;

// Our abstract template class
public abstract class Plant implements HabitObserver {
    public Plant(String name) {
        this.name = name;
        this.currentStage = PlantState.SEEDLING;
        clock = Clock.systemUTC();
    }

    private String name;
    private PlantState currentStage;
    private final Clock clock;

    // our template function 1
    public final void grow() {
        switch (currentStage) {
            case PlantState.SEEDLING -> {
                System.out.println("Growing");
                currentStage = PlantState.GROWING;
            }
            case PlantState.GROWING -> {
                System.out.println("Matured!");
                currentStage = PlantState.MATURE;
            }
            case PlantState.MATURE -> {
                System.out.println("Continue to maintain your mature plant");
            }
            case DEAD ->  {
                System.out.println("Your habit already died think about planting a new one");
            }
        }
    }

    // our template function 2
    public final void wither() {
        switch (currentStage) {
            case PlantState.SEEDLING -> {
                currentStage = PlantState.DEAD;
            }
            case PlantState.GROWING -> {
//                currentStage = PlantState.MATURE;
            }
            case PlantState.MATURE -> {

            }
            case PlantState.DEAD -> {}
        }

    }

    abstract protected void absorbWater();
    abstract protected void loseNourishment();
    abstract protected void celebrateGrowth();


}
