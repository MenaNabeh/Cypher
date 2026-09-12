/*
    Ant is an Abstract class. 
    It provides shared properties and behaviours for all types of ants.
*/
public abstract class Ant implements Entity {

    //The ant's current position on the grid
    protected int x;
    protected int y;

    //How fast the ant moves
    protected int speed;

    //The colony this ant belongs to
    protected Colony homeColony;

    protected MovementStrategy strategy = new RandomMovement();

    //Sets up an ant with a starting position, speed, and home colony. 
    public Ant(int x, int y, int speed, Colony homeColony) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.homeColony = homeColony;
    }

    //Moves the ant by dx, dy multiplied by its speed.
    public void move(int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
    }

    public void setStrategy(MovementStrategy strategy) {
        this.strategy = strategy;
    }

    //Returns the ants current X position.
   public int getX() {
        return x;
    }

    //Returns the ants current Y position
    public int getY() {
        return y;
    }

    @Override
    public void update(){
        strategy.move(this);
    }
}
