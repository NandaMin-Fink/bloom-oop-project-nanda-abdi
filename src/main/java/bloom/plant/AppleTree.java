package bloom.plant;

import bloom.Habit;
import org.slf4j.Logger;

import java.time.Clock;

public class AppleTree extends Plant {
    static Logger logger = org.slf4j.LoggerFactory.getLogger(AppleTree.class);

    public AppleTree(String name) {
        super(name);
    }

    public AppleTree(String name, Clock clock) {
        super(name, clock);
    }

    @Override
    protected void absorbWater() {
        logger.info("The apple tree absorbs water");
    }

    @Override
    protected void loseNourishment() {
        logger.info("The apple tree loses nourishment");
    }

    @Override
    protected void celebrateGrowth() {
        logger.info("Apple tree is fully grown!");
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
