public class RandomMovement implements MovementStrategy {

    @Override
    public void move(Ant ant) {
        int dx = (int)(Math.random() * 5) - 2;
        int dy = (int)(Math.random() * 5) - 2;
        ant.move(dx, dy);
    }
}

