//Represents a food source on the map.
public class FoodSource {

    // Food source position.
    private int x;
    private int y;

    // How much food is left.
    private int amountRemaining;

    // Creates a food source with a position and a starting amount.
    public FoodSource(int x, int y, int amountRemaining) {
        this.x = x;
        this.y = y;
        this.amountRemaining = amountRemaining;
    }

    // Checks if the food source has no food left.
    public boolean isDepleted() {
        return amountRemaining <= 0;
    }

    /*
     * Removes the food from source.
     * Only takes as much food as available (will never go negative).
     */
    public int take(int amount) throws DepletedFoodException {

        if (isDepleted()) {
            throw new DepletedFoodException(
                    "Food source is depleted.");
        }

        int taken = Math.min(amount, amountRemaining);
        amountRemaining -= taken;
        return taken;
    }

    // Returns food source X position.
    public int getX() {
        return x;
    }

    // Returns food source Y position.
    public int getY() {
        return y;
    }

    // Shows food source details.
    @Override
    public String toString() {
        return "FoodSource at (" + x + "," + y + ") remaining=" + amountRemaining;
    }
}