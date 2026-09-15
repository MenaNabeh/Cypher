public class QueenAnt extends Ant {

    public QueenAnt(int x, int y, int speed, Colony homeColony) {
        super(x, y, speed, homeColony);
    }

    @Override
    public void update() {
        // Queens barely move — small random wiggle
        int dx = (int)(Math.random() * 3) - 1;
        int dy = (int)(Math.random() * 3) - 1;

        // Keep queen very close to colony
        if (Math.abs(x - homeColony.getX()) < 10 &&
            Math.abs(y - homeColony.getY()) < 10) {
            move(dx, dy);
        }
    }

    @Override
    public String toString() {
        return "QueenAnt at (" + x + "," + y + ")";
    }
}

