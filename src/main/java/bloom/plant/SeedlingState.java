package bloom.plant;

public class SeedlingState implements PlantState {

    @Override
    public void handleGrow(Plant plant) {
        Plant.logger.info("Growing");
        plant.setStage(plant.getGrowingState()); // Fetches the injected state!
        plant.absorbWater();
    }

    @Override
    public void handleWither(Plant plant) {
        Plant.logger.info("Withered away");
        plant.setStage(plant.getDeadState()); // Fetches the injected state!
        plant.loseNourishment();
    }

    @Override
    public String getStageName() {
        return "SEEDLING";
    }
}