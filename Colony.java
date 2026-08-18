public class Colony {

    private int x;
    private int y;
    private int foodStored;

    public Colony(int x, int y) {
        this.x = x;
        this.y = y;
        this.foodStored = 0;
    }

    public void depositFood(int amount) {
        foodStored += amount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFoodStored() {
        return foodStored;
    }

    @Override
    public String toString() {
        return "Colony at (" + x + "," + y + ") food=" + foodStored;
    }
}