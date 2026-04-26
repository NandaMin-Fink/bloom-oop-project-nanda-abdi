package bloom.plant;

import java.time.Clock;

public class PlantFactory {

    private PlantFactory() { }
    private static void injectStates(Plant plant) {
        plant.setStates(
                new SeedlingState(),
                new GrowingState(),
                new MatureState(),
                new DeadState()
        );
    }

    public static Plant createRosePlant(String name) {
        return createRosePlant(name, Clock.systemUTC());
    }

    public static Plant createRosePlant(String name, Clock clock) {

        Plant plant= new RosePlant(name, clock);
        injectStates(plant);
        return plant;
    }


    public static Plant createAppleTree(String name) {
        return createAppleTree(name, Clock.systemUTC());
    }

    public static Plant createAppleTree(String name, Clock clock) {

        Plant plant= new AppleTree(name, clock);
        injectStates(plant);
        return plant;

    }

    public static Plant createMangoTree(String name) {
        return createMangoTree(name, Clock.systemUTC());
    }


    public static Plant createMangoTree(String name, Clock clock) {
        Plant plant= new MangoTree(name, clock);
        injectStates(plant);
        return plant;
    }
}
