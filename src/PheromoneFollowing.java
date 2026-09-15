public class PheromoneFollowing implements MovementStrategy {

    @Override
    public void move(Ant ant) {

        // strongest pheromone near the ant
        Pheromone strongest = SimulationPanel.getStrongestNearby(ant.getX(), ant.getY());

        if (strongest != null) {

            // Move toward the colony (not toward the pheromone)
            int dx = Integer.compare(ant.homeColony.getX(), ant.getX());
            int dy = Integer.compare(ant.homeColony.getY(), ant.getY());

            ant.move(dx, dy);

        } else {
            // fallback movement
            int dx = (int)(Math.random() * 3) - 1;
            int dy = (int)(Math.random() * 3) - 1;
            ant.move(dx, dy);
        }
    }
}
