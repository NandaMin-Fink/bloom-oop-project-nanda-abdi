package bloom.plant;

import java.time.Clock;

public class PlantFactory {

    private PlantFactory() { }

    public static Plant createRosePlant(String name) {
        return createRosePlant(name, Clock.systemUTC());
    }

    public static Plant createRosePlant(String name, Clock clock) {
        return new RosePlant(name, clock);
    }


    public static Plant createAppleTree(String name) {
        return createAppleTree(name, Clock.systemUTC());
    }

    public static Plant createAppleTree(String name, Clock clock) {
        return new AppleTree(name, clock);
    }

    public static Plant createMangoTree(String name) {
        return createMangoTree(name, Clock.systemUTC());
    }


    public static Plant createMangoTree(String name, Clock clock) {
        return new MangoTree(name, clock);
    }
}
