public class ForagerAnt {

    private int x;
    private int y;
    private int speed;
    private Colony homeColony;
    private boolean carryingFood;

    public ForagerAnt(int x, int y, int speed, Colony homeColony) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.homeColony = homeColony;
        this.carryingFood = false;
    }

    public void move(int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "ForagerAnt at (" + x + "," + y + ") carryingFood=" + carryingFood;
    }
}