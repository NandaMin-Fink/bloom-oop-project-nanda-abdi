package bloom.plant;

public class GrowingState implements PlantState {

    @Override
    public void handleGrow(Plant plant) {
        Plant.logger.info("Matured!");
        plant.setStage(plant.getMatureState());

        plant.celebrateGrowth();
    }

    @Override
    public void handleWither(Plant plant) {
        Plant.logger.info("Regressing");
        plant.setStage(plant.getSeedlingState());

        plant.loseNourishment();
    }

    @Override
    public String getStageName() {
        return "GROWING";
    }
}