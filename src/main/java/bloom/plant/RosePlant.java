package bloom.plant;

import bloom.Habit;

public class RosePlant extends Plant {
    public RosePlant(String name) {
        super(name);
    }

    @Override
    protected void absorbWater() {
        System.out.println("The rose plant absorbs water");
    }

    @Override
    protected void loseNourishment() {
        System.out.println("The rose plant loses nourishment");

    }

    @Override
    protected void celebrateGrowth() {
        System.out.println("Rose plant reaches its final stage!");

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
