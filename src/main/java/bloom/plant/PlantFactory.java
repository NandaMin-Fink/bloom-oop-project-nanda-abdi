package bloom.plant;

public class PlantFactory {

    private PlantFactory() { }

    public static Plant createPlant(String type, String name) {
        return switch (type.trim().toLowerCase()) {
            case "rose"  -> new RosePlant(name);
//            case "apple" -> new AppleTree(name);
//            case "mango" -> new MangoTree(name);
            default -> throw new IllegalArgumentException("Unknown plant type");
        };
    }
}
