import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// Panel that handles updating and rendering the ant simulation
public class SimulationPanel extends JPanel implements ActionListener {

    private static List<Pheromone> pheromones = new ArrayList<>();

    // Lists using Generics to hold ants and food sources
    private List<Ant> ants = new ArrayList<>();
    private List<FoodSource> foods = new ArrayList<>();
    private Colony colony;

    // Timer for simulation
    private Timer timer;

    // Allow ants to drop pheromones
    public static void addPheromone(Pheromone p) {
        pheromones.add(p);
    }

    // updates the speed of all ants in the simulation
    public void setAllAntSpeed(int speed) {
        for (Ant ant : ants) {
            ant.setSpeed(speed);
        }
    }

    // resets the simulation to its initial state
    public void resetSimulation(int antCount) {
        ants.clear();
        foods.clear();
        pheromones.clear();

        colony = new Colony(200, 200);
        foods.add(new FoodSource(100, 100, 10));
        foods.add(new FoodSource(300, 100, 15));

        for (int i = 0; i < antCount; i++) {
            ants.add(new ForagerAnt(200, 200, 4, colony));
        }
        for (int i = 0; i < antCount; i++) {
            ants.add(new ScoutAnt(200, 200, 4, colony));
        }
    }

    // Sets up the colony, food, ants, and starts the timer
    public SimulationPanel() {
        setBackground(new Color(76, 140, 80));
        resetSimulation(10);

        //Add one queen ant 
        // Add one queen
        ants.add(new QueenAnt(colony.getX(), colony.getY(), 1, colony));


        // Trigger an update every 150 milliseconds
        timer = new Timer(120, this);
        timer.start();
    }

    public static Pheromone getStrongestNearby(int x, int y) {
        Pheromone strongest = null;
        double bestStrength = 0;

        for (Pheromone p : pheromones) {
            int dx = Math.abs(p.getX() - x);
            int dy = Math.abs(p.getY() - y);

            // Only consider pheromones within a 50px radius
            if (dx < 50 && dy < 50) {
                if (p.getStrength() > bestStrength) {
                    bestStrength = p.getStrength();
                    strongest = p;
                }
            }
        }

        return strongest;
    }

    // Runs every timer tick to update position and interaction logic
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Pheromone p : pheromones) {
            p.decay();
        }
        pheromones.removeIf(Pheromone::isWeak);

        List<Ant> toAdd = new ArrayList<>();
        List<Ant> toRemove = new ArrayList<>();

        for (Ant ant : ants) {

            ant.update(); // Movement is now from strategy

            // Logic specific to forager ants collecting/depositing food
            if (ant instanceof ForagerAnt forager) {

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

            // Scout Logic
            if (ant instanceof ScoutAnt scout) {
                for (FoodSource food : foods) {
                    if (Math.abs(scout.getX() - food.getX()) < 15 && Math.abs(scout.getY() - food.getY()) < 15) {
                        try {
                            int taken = food.take(2);

                            if (taken > 0) {
                                // Placeholder transformation for now
                                Ant newAnt = new ForagerAnt(
                                        scout.getX(),
                                        scout.getY(),
                                        scout.speed,
                                        colony);

                                toRemove.add(scout);
                                toAdd.add(newAnt);

                                System.out.println("Scout collected food and became a Forager!");

                            }
                        } catch (DepletedFoodException ignored) {
                        }
                    }
                }
            }
        }
        ants.removeAll(toRemove);
        ants.addAll(toAdd);

        // replace any depleted food sources with a new food source else where
        int removedCount = foods.size();
        foods.removeIf(FoodSource::isDepleted);
        removedCount -= foods.size();
        for (int i = 0; i < removedCount; i++) {
            int newX = 50 + (int)(Math.random() * 300);
            int newY = 50 + (int)(Math.random() * 300);
            foods.add(new FoodSource(newX, newY, 10));
        }

        // Redraw screen after updates
        repaint();
    }

    // Draws all visual elements onto the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw colony
        int cx = colony.getX();
        int cy = colony.getY();

        // Layer 1: Largest Outer Boundary 
        g.setColor(new Color(50, 30, 10));
        g.fillRect(cx - 10, cy - 10, 40, 40);

        // Layer 2: Outer Base 
        g.setColor(new Color(80, 50, 20));
        g.fillRect(cx - 5, cy - 5, 30, 30);

        // Layer 3: Middle Ring 
        g.setColor(new Color(130, 85, 40));
        g.fillRect(cx, cy, 20, 20);

        // Layer 4: Inner Core 
        g.setColor(new Color(190, 140, 80));
        g.fillRect(cx + 5, cy + 5, 10, 10);

        // Display current food count text
        g.setColor(Color.BLACK);
        g.drawString("Colony Food Stored: " + colony.getFoodStored(), 10, 20);

        // Draw active food sources as layered triangles
                for (FoodSource f : foods) {
            if (!f.isDepleted()) {
                int x = f.getX();
                int y = f.getY();
                int size = 30; // Increased overall size

                // 1. Back triangle
                g.setColor(new Color(230, 175, 115));
                int[] backX = {x - 4, x + size + 4, x + (size / 2)};
                int[] backY = {y + size + 4, y + size + 4, y - 2};
                g.fillPolygon(backX, backY, 3);

                // Front triangle 
                g.setColor(new Color(195, 60, 50));
                int[] topX = {x, x + size, x + (size / 2)};
                int[] topY = {y + size, y + size, y};
                g.fillPolygon(topX, topY, 3);

                // Pepperoni
                g.setColor(new Color(130, 25, 20));
                int spotSize = 8;
                g.fillOval(x + (size / 2) - 4, y + 7, spotSize, spotSize);             // Top spot near upper tip
                g.fillOval(x + 3, y + size - 14, spotSize, spotSize);                  // Bottom-left spot near base
                g.fillOval(x + size - 11, y + size - 14, spotSize, spotSize);          // Bottom-right spot near base
                }
        }
        // Label showing how much food is left at each source.
        g.setColor(Color.BLACK);
        for (FoodSource f : foods) {
            if (!f.isDepleted()) {
                g.drawString(String.valueOf(f.getAmountRemaining()), f.getX(), f.getY() - 3);
            }
        }
        // Draw pheromones (very faint red)
        g.setColor(new Color(255, 0, 0, 15));
        for (Pheromone p : pheromones) {
            int size = (int) (10 * p.getStrength());
            g.fillOval(p.getX(), p.getY(), size, size);
        }

        for (Ant ant : ants) {
            if (ant instanceof ForagerAnt) {
                g.setColor(Color.PINK);
            } else if (ant instanceof ScoutAnt) {
                g.setColor(new Color(173, 216, 230));
            } else if (ant instanceof QueenAnt) {
                g.setColor(new Color(128, 0, 128)); // purple queen
            } else {
                g.setColor(Color.GRAY);
            }

            g.fillOval(ant.getX(), ant.getY(), 6, 6);
        }

    }

    // Program entry point: Creates window and adds panel
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ant Colony Simulation");
        SimulationPanel panel = new SimulationPanel();

        // BorderLayout so simulation sits in the center and controls on the right
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        // Control panel for adjusting controls
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

        // Slider to adjust ant speed
        JLabel speedLabel = new JLabel("Set Ant Speed:");
        JSlider speedSlider = new JSlider(1, 5, 2);
        speedSlider.addChangeListener(e -> panel.setAllAntSpeed(speedSlider.getValue()));

        // Slider to adjust Number of ants
        JLabel antCountLabel = new JLabel("Number of Ants:");
        JSlider antCountSlider = new JSlider(1, 40, 20);

        // Reset button to reset simulation
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> panel.resetSimulation(antCountSlider.getValue()));

        // Add controls into frame
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
        controlPanel.add(antCountLabel);
        controlPanel.add(antCountSlider);
        controlPanel.add(resetButton);
        frame.add(controlPanel, BorderLayout.EAST);

        frame.setSize(550, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
