public abstract class Ant {

    protected int x;
    protected int y;
    protected int speed;
    protected Colony homeColony;

    public Ant(int x, int y, int speed, Colony homeColony) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.homeColony = homeColony;
    }

    public void move(int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
    }

   public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
