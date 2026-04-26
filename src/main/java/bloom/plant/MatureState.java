package bloom.plant;

public class MatureState implements PlantState {

    @Override
    public void handleGrow(Plant plant) {
        Plant.logger.info("Continue to maintain your matured plant");

    }

    @Override
    public void handleWither(Plant plant) {
        Plant.logger.info("Plant lost some growth");
        plant.setStage(plant.getGrowingState());

        plant.loseNourishment();
    }
    @Override
    public String getStageName() {
        return "MATURE";
    }
}