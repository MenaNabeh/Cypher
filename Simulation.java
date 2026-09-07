//Main simulation demonstrating how the ants behave.
public class Simulation {

    public static void main(String[] args) {

        //Create a colony at (0,0).
        Colony colony = new Colony(0, 0);

        //Create a food source at (10,10) with 20 food.
        FoodSource food = new FoodSource(10, 10, 20);

        //Create a forager ant and a scout ant 
        ForagerAnt forager = new ForagerAnt(0, 0, 1, colony);
        ScoutAnt scout = new ScoutAnt(0, 0, 2, colony);

        //Move the forager ant and attempt to collect food. 
        forager.move(1, 1);
        forager.pickUpFood(food);
        forager.returnToColony();

        //Move the scout ant twice and report findings. 
        scout.move(2, 0);
        scout.move(0, 2);
        scout.reportFindings();

        //Print the final state of everything
        System.out.println(colony);
        System.out.println(food);
        System.out.println(forager);
        System.out.println(scout);
    }
}