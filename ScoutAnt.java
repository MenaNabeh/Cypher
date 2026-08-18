public class ScoutAnt extends Ant {

    private int tilesExplored;

    public ScoutAnt(int x, int y, int speed, Colony homeColony) {
        super(x, y, speed, homeColony);
        this.tilesExplored = 0;
    }

    @Override
    public void move(int dx, int dy) {
      super.move(dx, dy);
        tilesExplored++;
    }

    public void reportFindings() {
        System.out.println("Scout reports " + tilesExplored + " tiles explored to colony at ("
                + homeColony.getX() + "," + homeColony.getY() + ")");
    }

    @Override
    public String toString() {
        return "ScoutAnt at (" + x + "," + y + ") tilesExplored=" + tilesExplored;
    }
}