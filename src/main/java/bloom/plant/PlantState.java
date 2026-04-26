package bloom.plant;

public interface PlantState {
    void handleGrow(Plant plant);
    void handleWither(Plant plant);
    String getStageName();
}