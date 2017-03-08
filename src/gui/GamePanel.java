package gui;

import program.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by KhoaBeo on 2/22/2017.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    private Thread thread;
    private boolean isRunning;
    private GameManager gameManager;

    public GamePanel() {
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);

        gameManager = new GameManager();

        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (isRunning) {
            repaint();
            gameManager.run();

            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        super.paintComponent(graphics);
        gameManager.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        gameManager.getBitSet().set(keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameManager.getBitSet().clear(keyEvent.getKeyCode());
    }
}
