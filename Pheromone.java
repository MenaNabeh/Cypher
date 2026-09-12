public class Pheromone {

    private int x;
    private int y;
    private double strength;

    public Pheromone(int x, int y) {
        this.x = x;
        this.y = y;
        this.strength = 1.0;
    }

    public void decay() {
        strength *= 0.998;
    }

    public boolean isWeak() {
        return strength < 0.1;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public double getStrength() { return strength; }
}
