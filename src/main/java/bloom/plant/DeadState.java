package bloom.plant;

public class DeadState implements PlantState {

    @Override
    public void handleGrow(Plant plant) {
        Plant.logger.info("Your habit already died");
    }

    @Override
    public void handleWither(Plant plant) {
    }
    @Override
    public String getStageName() {
        return "DEAD";
    }
}