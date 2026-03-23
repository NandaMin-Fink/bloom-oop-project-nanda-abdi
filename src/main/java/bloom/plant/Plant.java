package bloom.plant;

import bloom.Habit;
import bloom.observer.HabitObserver;

import java.time.Clock;

// Our abstract template class
public abstract class Plant implements HabitObserver {

    private String name;
    private PlantState currentStage;
    private final Clock clock;

    public Plant(String name) {
        this.name = name;
        this.currentStage = PlantState.SEEDLING;
        clock = Clock.systemUTC();
    }


    // our template function 1
    public final void grow() {
        switch (currentStage) {
            case SEEDLING -> {
                System.out.println("Growing");
                currentStage = PlantState.GROWING;
                absorbWater();

            }
            case GROWING -> {
                System.out.println("Matured!");
                currentStage = PlantState.MATURE;
                celebrateGrowth();
            }
            case MATURE -> {
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
            case SEEDLING -> {
                currentStage = PlantState.DEAD;
                loseNourishment();
            }
            case GROWING -> {
                currentStage = PlantState.SEEDLING;
                loseNourishment();
            }
            case MATURE -> {
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
