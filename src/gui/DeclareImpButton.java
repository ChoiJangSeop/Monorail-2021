package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DeclareImpButton implements ControlButton {

    private JButton declareImpButton = new JButton();

    public DeclareImpButton() {
        super();

        declareImpButton.setPreferredSize(new Dimension(150, 150));
        declareImpButton.setIcon(ImgStore.getInstance().getImg("ImpossibleImg"));
        declareImpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage("ImpossibleDeclare");
            }
        });
    }

    @Override
    public JButton getButton() { return this.declareImpButton; }

    @Override
    public JButton getButton(int posx, int posy) { return this.declareImpButton; }
}