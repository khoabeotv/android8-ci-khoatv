package gui;

import javax.swing.*;

/**
 * Created by KhoaBeo on 2/22/2017.
 */
public class GameFrame extends JFrame {
    public static final int WIDTH_F = 500;
    public static final int HEIGHT_F = 700;
    private GamePanel gamePanel;

    public GameFrame() {
        setSize(WIDTH_F, HEIGHT_F);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);
    }
}
