package gui;


import service.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;


/**
 * Created by KhoaBeo on 2/22/2017.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    private GameManager gameManager;
    private Thread thread;
    private boolean isRunning;


    public GamePanel() {
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
            gameManager.event();

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
        gameManager.drawImage(g2d);
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
