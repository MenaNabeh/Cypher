import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Panel that handles updating and rendering the ant simulation
public class SimulationPanel extends JPanel implements ActionListener {

    // Lists using Generics to hold ants and food sources
    private List<Ant> ants = new ArrayList<>();
    private List<FoodSource> foods = new ArrayList<>();
    private Colony colony;
    private Timer timer;

    // Sets up the colony, food, ants, and starts the timer
    public SimulationPanel() {
        setBackground(Color.WHITE);

        // Place colony and food sources on the screen
        colony = new Colony(200, 200);
        foods.add(new FoodSource(100, 100, 10));
        foods.add(new FoodSource(300, 100, 15));

        // Add ants to the simulation
        for (int i = 0; i < 20; i++) {
            ants.add(new ForagerAnt(200, 200, 2, colony));
        }
        // Trigger an update every 100 milliseconds
        timer = new Timer(100, this);
        timer.start();
    }

    // Runs every timer tick to update position and interaction logic
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Ant ant : ants) {
            // Move ants in a small random direction
            int dx = (int) (Math.random() * 5) - 2;
            int dy = (int) (Math.random() * 5) - 2;
            ant.move(dx, dy);

            // Logic specific to worker ants collecting/depositing food
            if (ant instanceof ForagerAnt) {
                ForagerAnt forager = (ForagerAnt) ant;

                // Pick up food if close to a source
                for (FoodSource food : foods) {
                    if (Math.abs(forager.getX() - food.getX()) < 15 && Math.abs(forager.getY() - food.getY()) < 15) {
                        forager.pickUpFood(food);
                    }
                }

                // Deposit food if close to home colony
                if (Math.abs(forager.getX() - colony.getX()) < 15 && Math.abs(forager.getY() - colony.getY()) < 15) {
                    forager.returnToColony();
                }
            }
        }
        // Redraw screen after updates
        repaint();
    }

    // Draws all visual elements onto the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw colony as a blue square and display current food count
        g.setColor(Color.BLUE);
        g.fillRect(colony.getX(), colony.getY(), 20, 20);
        g.drawString("Colony Food Stored: " + colony.getFoodStored(), 10, 20);

        // Draw active food sources as green circles
        g.setColor(Color.GREEN);
        for (FoodSource f : foods) {
            if (!f.isDepleted()) {
                g.fillOval(f.getX(), f.getY(), 15, 15);
            }
        }

        // Draw ants as small black dots
        g.setColor(Color.BLACK);
        for (Ant ant : ants) {
            g.fillOval(ant.getX(), ant.getY(), 6, 6);
        }
    }

    // Program entry point: Creates window and adds panel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ant Colony Simulation");
        SimulationPanel panel = new SimulationPanel();
        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}