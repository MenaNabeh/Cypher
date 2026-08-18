public class ScoutAnt {

    private int x;
    private int y;
    private int speed;
    private Colony homeColony;
    private int tilesExplored;

    public ScoutAnt(int x, int y, int speed, Colony homeColony) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.homeColony = homeColony;
        this.tilesExplored = 0;
    }

    public void move(int dx, int dy) {
        x += dx * speed;
        y += dy * speed;
        tilesExplored++;
    }

    public void reportFindings() {
        System.out.println("Scout reports " + tilesExplored + " tiles explored to colony at ("
                + homeColony.getX() + "," + homeColony.getY() + ")");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "ScoutAnt at (" + x + "," + y + ") tilesExplored=" + tilesExplored;
    }
}