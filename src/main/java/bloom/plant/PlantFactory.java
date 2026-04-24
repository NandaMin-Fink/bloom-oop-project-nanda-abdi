package bloom.plant;

import java.time.Clock;

public class PlantFactory {

    private PlantFactory() { }

    public static Plant createPlant(String type, String name) {
        return createPlant(type, name, Clock.systemUTC());
    }

    public static Plant createPlant(String type, String name, Clock clock) {
        return switch (type.trim().toLowerCase()) {
            case "rose"  -> new RosePlant(name, clock);
            case "apple" -> new AppleTree(name, clock);
            case "mango" -> new MangoTree(name, clock);
            default -> throw new IllegalArgumentException("Unknown plant type");
        };
    }
}
