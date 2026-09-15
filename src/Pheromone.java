public class Pheromone {

    private int x;
    private int y;
    private double strength;

    public Pheromone(int x, int y, double strength) {
        this.x = x;
        this.y = y;
        this.strength = strength;
    }

    public void decay() {
        strength *= 0.995;
    }

    public boolean isWeak() {
        return strength < 0.1;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public double getStrength() { return strength; }
}
