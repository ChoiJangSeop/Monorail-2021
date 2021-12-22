package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.util.List;
import java.util.ArrayList;

public class Ground implements ControlButton {
    
    private JButton[] groundBtn = new JButton[187];
    private SelectMode selectMode = new SelectMode();

    public Ground() {
        for (int i=0; i<187; ++i) {
            groundBtn[i] = new JButton();

            groundBtn[i].setPreferredSize(new Dimension(50, 50));
            groundBtn[i].setName("" + i);
            groundBtn[i].setIcon(ImgStore.getInstance().getImg("Ground"));

            if (i == 93 || i == 92) {
                groundBtn[i].setIcon(ImgStore.getInstance().getRail(0));
            }

            groundBtn[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton b = (JButton)e.getSource();
                    int pos = Integer.parseInt(b.getName());
                    
                    // add arguments
                    List<Integer> args = new ArrayList<>();
                    args.add(pos/17); args.add(pos%17);
                    args.add(selectMode.getTileType());
                    b.setIcon(ImgStore.getInstance().getRail(selectMode.getTileType()));
                    
                    selectMode.initTileType();

                    sendMessage("PutTile", args);
                }
            });
        }
    }

    @Override
    public JButton getButton() { return this.groundBtn[0]; }    // cannot use

    @Override
    public JButton getSubButton(int idx) { 
        return this.selectMode.getButton(idx); 
    }
    
    @Override
    public JButton getButton(int posx, int posy) { return this.groundBtn[posx*17 + posy]; }

}