package bloom.plant;

import org.slf4j.Logger;

import java.time.Clock;

public class RosePlant extends Plant {
    static Logger logger = org.slf4j.LoggerFactory.getLogger(RosePlant.class);

    public RosePlant(String name) {
        super(name);
    }

    public RosePlant(String name, Clock clock) {
        super(name, clock);
    }

    @Override
    protected void absorbWater() {
        logger.info("The rose plant absorbs water");
    }

    @Override
    protected void loseNourishment() {
        logger.info("The rose plant loses some nourishment");

    }

    @Override
    protected void celebrateGrowth() {
        logger.info("Rose plant achieved final stage; a beautiful rose");

    }

}
