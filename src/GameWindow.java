import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.BitSet;

/**
 * Created by KhoaBeo on 2/19/2017.
 */
public class GameWindow extends JFrame implements Runnable {
    private JPanel panel;
    private Image backgroundImage;
    private Image planeImage;
    private int planeX;
    private int planeY;
    private BitSet bitSet;
    private Thread thread;
    private boolean isRunning;

    public GameWindow() {
        setVisible(true);
        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g2d = (Graphics2D) graphics;
                super.paintComponent(graphics);
                g2d.drawImage(backgroundImage, 0, 0, 400, 600, null);
                g2d.drawImage(planeImage, planeX, planeY, null);
            }
        };
        panel.setLayout(null);
        panel.setFocusable(true);
        add(panel);

        backgroundImage = loadImageFromRes("background.png");
        planeImage = loadImageFromRes("plane2.png");
        planeX = (400 - planeImage.getWidth(null)) / 2;
        planeY = 600 - planeImage.getHeight(null) - 50;
        bitSet = new BitSet(256);
        isRunning = true;
        thread = new Thread(this);
        thread.start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                super.windowClosing(windowEvent);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
                super.windowClosed(windowEvent);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                super.keyPressed(keyEvent);
                bitSet.set(keyEvent.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                super.keyReleased(keyEvent);
                bitSet.clear(keyEvent.getKeyCode());
            }
        });
    }

    private Image loadImageFromRes(String url) {
        try {
            Image image = ImageIO.read(new File("resources/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void move(String orient) {
        int x = planeX;
        int y = planeY;

        switch (orient) {
            case "RIGHT":
                x += 2;
                break;
            case "LEFT":
                x -= 2;
                break;
            case "DOWN":
                y += 2;
                break;
            case "UP":
                y -= 2;
                break;
            default:
                break;
        }

        if (x < 0 || x > 400 - planeImage.getWidth(null)) {
            planeY = y;
            return;
        }

        if (y < 0 || y > 600 - planeImage.getHeight(null)) {
            planeX = x;
            return;
        }

        planeX = x;
        planeY = y;
    }

    @Override
    public void run() {
        while (isRunning) {
            panel.repaint();

            if (bitSet.get(KeyEvent.VK_UP)) {
                move("UP");
            }
            if (bitSet.get(KeyEvent.VK_DOWN)) {
                move("DOWN");
            }
            if (bitSet.get(KeyEvent.VK_RIGHT)) {
                move("RIGHT");
            }
            if (bitSet.get(KeyEvent.VK_LEFT)) {
                move("LEFT");
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
