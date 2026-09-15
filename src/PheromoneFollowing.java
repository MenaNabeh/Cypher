public class PheromoneFollowing implements MovementStrategy {

    @Override
    public void move(Ant ant) {

        Pheromone strongest = SimulationPanel.getStrongestNearby(ant.getX(), ant.getY());

        if (strongest != null && strongest.getStrength() >= 1.0) {

            // SNAP to the pheromone trail
            int dx = Integer.compare(strongest.getX(), ant.getX());
            int dy = Integer.compare(strongest.getY(), ant.getY());

            ant.move(dx, dy);
            return;
        }

        // No strong trail → wander
        int dx = (int)(Math.random() * 3) - 1;
        int dy = (int)(Math.random() * 3) - 1;
        ant.move(dx, dy);
    }
}

