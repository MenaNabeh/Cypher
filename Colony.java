//Represents the ant colony.
public class Colony {

    //Colony position on the grid.
    private int x;
    private int y;

    //How much food the colony has stored.
    private int foodStored;

    //Creates a colony at a given position with 0 food.
    public Colony(int x, int y) {
        this.x = x;
        this.y = y;
        this.foodStored = 0;
    }

    //Adds food to the colony's storage.
    public void depositFood(int amount) {
        foodStored += amount;
    }

    //Returns colony X position.
    public int getX() {
        return x;
    }

    //Returns colony Y position.
    public int getY() {
        return y;
    }

    //Returns how much food the colony has
    public int getFoodStored() {
        return foodStored;
    }

    //Converts colony information into a readable string
    @Override
    public String toString() {
        return "Colony at (" + x + "," + y + ") food=" + foodStored;
    }
}