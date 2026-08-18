public class Simulation {

    public static void main(String[] args) {
        Colony colony = new Colony(0, 0);
        FoodSource food = new FoodSource(10, 10, 20);

        ForagerAnt forager = new ForagerAnt(0, 0, 1, colony);
        ScoutAnt scout = new ScoutAnt(0, 0, 2, colony);

        forager.move(1, 1);
        forager.pickUpFood(food);
        forager.returnToColony();

        scout.move(2, 0);
        scout.move(0, 2);
        scout.reportFindings();

        System.out.println(colony);
        System.out.println(food);
        System.out.println(forager);
        System.out.println(scout);
    }
}