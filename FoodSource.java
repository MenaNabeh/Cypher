public class FoodSource {

    private int x;
    private int y;
    private int amountRemaining;

    public FoodSource(int x, int y, int amountRemaining) {
        this.x = x;
        this.y = y;
        this.amountRemaining = amountRemaining;
    }

    public boolean isDepleted() {
        return amountRemaining <= 0;
    }

    public int take(int amount) {
        int taken = Math.min(amount, amountRemaining);
        amountRemaining -= taken;
        return taken;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "FoodSource at (" + x + "," + y + ") remaining=" + amountRemaining;
    }
}