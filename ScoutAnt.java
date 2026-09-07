/*
    A Scout Ant explores the map and reports how many tiles it has visited. 
*/
public class ScoutAnt extends Ant {

    //Counts how many moves (tiles explored) the scout has made.
    private int tilesExplored;

    //Creates a scout ant 
    public ScoutAnt(int x, int y, int speed, Colony homeColony) {
        super(x, y, speed, homeColony);
        this.tilesExplored = 0;
    }

    //Moves the scout and increases the explored tile count. 
    @Override
    public void move(int dx, int dy) {
      super.move(dx, dy);
        tilesExplored++;
    }

    //Prints how many tiles the scout has explored.
    public void reportFindings() {
        System.out.println("Scout reports " + tilesExplored + " tiles explored to colony at ("
                + homeColony.getX() + "," + homeColony.getY() + ")");
    }

    //Shows scount ant details.
    @Override
    public String toString() {
        return "ScoutAnt at (" + x + "," + y + ") tilesExplored=" + tilesExplored;
    }
}