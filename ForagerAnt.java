public class ForagerAnt extends Ant {

    private boolean carryingFood;

    public ForagerAnt(int x, int y, int speed, Colony homeColony) {
        super(x, y, speed, homeColony);
        this.carryingFood = false;
    }

    public void pickUpFood(FoodSource food) {
        int taken = food.take(5);
        if (taken > 0) {
            carryingFood = true;
        }
    }

    public void returnToColony() {
        if (carryingFood) {
            homeColony.depositFood(5);
            carryingFood = false;
        }
    }

    @Override
    public String toString() {
        return "ForagerAnt at (" + x + "," + y + ") carryingFood=" + carryingFood;
    }
}