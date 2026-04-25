package bloom.plant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RosePlantTest {

    private RosePlant testRose;

    @BeforeEach
    void setUp() {
        testRose = new RosePlant(" Rose");
    }

    @Test
    void testGrowIncreasesStage() {
        testRose.grow();
        assertEquals(PlantState.GROWING, testRose.getCurrentStage());
    }

    @Test
    void testWitherDecreasesStage() {
        testRose.grow();
        testRose.wither();
        assertEquals(PlantState.SEEDLING, testRose.getCurrentStage());
    }
}