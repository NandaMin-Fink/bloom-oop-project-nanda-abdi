package bloom.plant;

import bloom.Habit;
import org.slf4j.Logger;

import java.time.Clock;

public class MangoTree extends Plant {
    static Logger logger = org.slf4j.LoggerFactory.getLogger(MangoTree.class);

    public MangoTree(String name) {
        super(name);
    }

    public MangoTree(String name, Clock clock) {
        super(name, clock);
    }

    @Override
    protected void absorbWater() {
        logger.info("The mango tree absorbs water");
    }

    @Override
    protected void loseNourishment() {
        logger.info("The mango tree loses nourishment");
    }

    @Override
    protected void celebrateGrowth() {
        logger.info("Mango tree reaches its final stage!");
    }

    @Override
    public void onHabitCompleted(Habit habit) {
        absorbWater();
    }

    @Override
    public void onHabitNeglected(Habit habit) {
        loseNourishment();
    }
}
