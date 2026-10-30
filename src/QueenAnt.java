public class QueenAnt extends Ant {

    public QueenAnt(int x, int y, int speed, Colony homeColony) {
        super(x, y, speed, homeColony);
    }

    @Override
    public void update() {
        // Queens barely move — small random wiggle
        int dx = (int) (Math.random() * 3) - 1;
        int dy = (int) (Math.random() * 3) - 1;
        move(dx, dy);

        // Keep queen very close to colony
        if (Math.abs(x - homeColony.getX()) > 10 || Math.abs(y - homeColony.getY()) > 10) {
            int backX = Integer.compare(homeColony.getX(), x);
            int backY = Integer.compare(homeColony.getY(), y);
            move(backX, backY);
        }
    }

    @Override
    public String toString() {
        return "QueenAnt at (" + x + "," + y + ")";
    }
}
