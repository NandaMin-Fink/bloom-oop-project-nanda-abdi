package bloom.plant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RosePlantTest {

    private RosePlant testRose;

    @BeforeEach
    void setUp() {
        testRose = new RosePlant("Test Rose");
    }

    @Test
     void testInitialStateIsSeed() {
        assertEquals(PlantState.SEEDLING, testRose.getCurrentStage(), "Plant should start at stage 0 (Seed)");
    }

    @Test
    void testGrowIncreasesStage() {
        testRose.grow();
        assertEquals(PlantState.GROWING, testRose.getCurrentStage(), "Plant should grow to stage 1 (Sprout)");
    }

    @Test
    void testWitherDecreasesStage() {
        testRose.grow();
        testRose.wither();
        assertEquals(PlantState.SEEDLING, testRose.getCurrentStage(), "Plant should wither back down to stage 0");
    }

    @Test
    void testMaxGrowthBoundary() {
        testRose.grow();
        testRose.grow();
        testRose.grow();
        testRose.grow();
        assertEquals(PlantState.MATURE, testRose.getCurrentStage(), "Plant should not exceed maximum growth stage of 3");
    }

    @Test
    void testMinWitherBoundary() {
        testRose.wither();
        assertEquals(PlantState.DEAD, testRose.getCurrentStage(), "Plant should not wither below stage 0");
    }
}