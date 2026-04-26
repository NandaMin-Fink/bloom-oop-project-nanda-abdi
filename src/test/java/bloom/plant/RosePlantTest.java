package bloom.plant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Clock;
import static org.junit.jupiter.api.Assertions.*;

class RosePlantTest {

    private Plant testRose;

    @BeforeEach
    void setUp() {
        testRose = PlantFactory.createRosePlant("Test Rose", Clock.systemUTC());
    }

    @Test
    void testGrowIncreasesStage() {
        testRose.grow();
        assertEquals("GROWING", testRose.getCurrentStage().getStageName());
    }

    @Test
    void testFullGrowthToMature() {
        assertEquals("SEEDLING", testRose.getCurrentStage().getStageName());
        testRose.grow();
        assertEquals("GROWING", testRose.getCurrentStage().getStageName());
        testRose.grow();
        assertEquals("MATURE", testRose.getCurrentStage().getStageName());
        testRose.grow();
        assertEquals("MATURE", testRose.getCurrentStage().getStageName());
    }

    @Test
    void testWitherDecreasesStage() {
        testRose.grow();
        testRose.wither();
        assertEquals("SEEDLING", testRose.getCurrentStage().getStageName());
    }

    @Test
    void testReachesMatureState() {
        testRose.grow();
        testRose.grow();
        assertEquals("MATURE", testRose.getCurrentStage().getStageName());
    }

    @Test
    void testPlantCanDie() {
        testRose.wither();
        testRose.wither();

        assertEquals("DEAD", testRose.getCurrentStage().getStageName());
    }

    @Test
    void testRegressionToDead() {
        assertEquals("SEEDLING", testRose.getCurrentStage().getStageName());
        testRose.wither();
        assertEquals("DEAD", testRose.getCurrentStage().getStageName());

        testRose.grow();
        assertEquals("DEAD", testRose.getCurrentStage().getStageName());
    }


    @Test
    void testMatureRegressesToGrowing() {
        testRose.grow();
        testRose.grow();
        testRose.wither();
        assertEquals("GROWING", testRose.getCurrentStage().getStageName());
    }

}