//A Forager Ant collects food and brings it back to the colony.
public class ForagerAnt extends Ant {

    // Checks whether the ant is currently carrying food.
    private boolean carryingFood;

    /*
     * Creates a forager ant starting at a position,
     * with a speed and a home colony.
     */
    public ForagerAnt(int x, int y, int speed, Colony homeColony) {
        super(x, y, speed, homeColony);
        this.carryingFood = false;
    }

    /*
     * Attempts to pick up food from a food source.
     * Takes up to 5 units if available.
     * 
     * If the food source is depleted, a message is printed to the console.
     */
    public void pickUpFood(FoodSource food) {
        try {
            int taken = food.take(5);
            if (taken > 0) {
                carryingFood = true;
            }
        } catch (DepletedFoodException e) {
            System.out.println(this + " found nothing: " + e.getMessage());
        }
    }

    // Lets other code check if the ant is carrying food.
    public boolean isCarryingFood() {
        return carryingFood;
    }

    // Returns food to the colony if carrying any.
    public void returnToColony() {
        if (carryingFood) {
            homeColony.depositFood(5);
            carryingFood = false;
        }
    }
    
    @Override
    public void update(){

        //Switch strategy based on carrying food
        if(carryingFood){
            setStrategy(new PheromoneFollowing());
        } else{
            setStrategy(new RandomMovement());
        }

        strategy.move(this);

        SimulationPanel.addPheromone(new Pheromone(x,y));
    }

    // Shows forager ant details.
    @Override
    public String toString() {
        return "ForagerAnt at (" + x + "," + y + ") carryingFood=" + carryingFood;
    }
}