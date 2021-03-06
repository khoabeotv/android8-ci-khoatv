package gui;

import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by KhoaBeo on 3/9/2017.
 */
public class GameOverPanel extends JPanel {

    private JLabel btnCombatFinish;

    public GameOverPanel() {
        setLayout(null);
        initComp();
    }

    private void initComp() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (mouseEvent.getSource().equals(btnCombatFinish)) {
                    GameFrame.mainPanel.showPanel(MainPanel.TAG_MENU);
                }
            }
        };

        ImageIcon icon = new ImageIcon("resources/end_en.png");
        btnCombatFinish = new JLabel(icon);
        btnCombatFinish.setBounds((GameFrame.WIDTH_F - icon.getIconWidth()) / 2, 515, icon.getIconWidth(), icon.getIconHeight());
        btnCombatFinish.setFocusable(false);
        add(btnCombatFinish);
        btnCombatFinish.addMouseListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Image image = Utils.loadImageFromRes("ui_board.png");
        graphics.drawImage(image, 0, 0, GameFrame.WIDTH_F, GameFrame.HEIGHT_F, null);

        image = Utils.loadImageFromRes("gameover.png");
        graphics.drawImage(image, (GameFrame.WIDTH_F - image.getWidth(null)) / 2, 0, image.getWidth(null), image.getHeight(null), null);
    }
}
