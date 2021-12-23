package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import system.MainSystem;

public class DeclareImpButton extends ControlButton {

    private JButton declareImpButton = new JButton();

    public DeclareImpButton(MainSystem mainSystem) {
        super(mainSystem);

        declareImpButton.setPreferredSize(new Dimension(150, 150));
        declareImpButton.setIcon(ImgStore.getInstance().getImg("ImpossibleImg"));
        declareImpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendMessage(mainSystem, "ImpossibleDeclare");
            }
        });
    }

    @Override
    public JButton getButton() { return this.declareImpButton; }

    @Override
    public JButton getButton(int posx, int posy) { return this.declareImpButton; }
}