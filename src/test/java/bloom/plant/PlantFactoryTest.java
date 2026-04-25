package bloom.plant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlantFactoryTest {

    @Test
    void testCreateRose() {
        Plant p = PlantFactory.createRosePlant("Test Rose");
        assertTrue(p instanceof RosePlant);
    }

    @Test
    void testCreateApple() {
        Plant p = PlantFactory.createAppleTree("Apple");
        assertTrue(p instanceof AppleTree);
    }

    @Test
    void testCreateMango() {
        Plant p = PlantFactory.createMangoTree("Mango");
        assertTrue(p instanceof MangoTree);
    }

}
