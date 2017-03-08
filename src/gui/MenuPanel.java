package gui;


import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by KhoaBeo on 3/9/2017.
 */
public class MenuPanel extends JPanel {
    private JLabel btnStart;
    private JLabel btnExit;

    public MenuPanel() {
        setLayout(null);
        initComp();
    }

    private void initComp() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (mouseEvent.getSource().equals(btnStart)) {
                    GameFrame.mainPanel.showPanel(MainPanel.TAG_GAME);
                }
                if (mouseEvent.getSource().equals(btnExit)) {
                    System.exit(0);
                }
            }
        };

        ImageIcon icon = new ImageIcon("resources/start_en.png");
        btnStart = new JLabel(icon);
        btnStart.setBounds((GameFrame.WIDTH_F - icon.getIconWidth()) / 2 , 400, icon.getIconWidth(), icon.getIconHeight());
        btnStart.setFocusable(false);
        add(btnStart);
        btnStart.addMouseListener(mouseAdapter);

        icon = new ImageIcon("resources/exit_btn.png");
        btnExit = new JLabel(icon);
        btnExit.setBounds((GameFrame.WIDTH_F - icon.getIconWidth()) / 2 , btnStart.getY() + btnStart.getHeight() + 20, icon.getIconWidth(), icon.getIconHeight());
        btnExit.setFocusable(false);
        add(btnExit);
        btnExit.addMouseListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Image image = Utils.loadImageFromRes("menubg.png");
        graphics.drawImage(image, 0, 0, GameFrame.WIDTH_F, GameFrame.HEIGHT_F, null);

        image = Utils.loadImageFromRes("logo.png");
        graphics.drawImage(image, (GameFrame.WIDTH_F - image.getWidth(null)) / 2, 0, image.getWidth(null), image.getHeight(null), null);
    }
}
