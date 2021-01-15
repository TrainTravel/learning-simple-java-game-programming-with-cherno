package rain;


import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    // Resolution
    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;

    private boolean running = false;
    private Thread thread;
    private JFrame frame;

    // Constructor
    public Game() {
        Dimension size = new Dimension(width*scale, height*scale);
        setPreferredSize(size);

        frame = new JFrame();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() throws InterruptedException {
        thread.join();
    }

    public void run() {
        while(running) {
            System.out.println("Running...");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        // Since Game is a subclass of Canvas, it can be added(as a component) to fill the frame(window).
        game.frame.add(game);
        game.frame.pack();  // Make window fit the size of this subcomponent "game".
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminate the app when the X button is hit.
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true); // Make sure it's showing.

        game.start();
    }

}


