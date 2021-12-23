package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.util.List;
import java.util.ArrayList;

import system.MainSystem;

public class Ground extends ControlButton {
    
    private JButton[] groundBtn = new JButton[187];
    private SelectMode selectMode = new SelectMode();

    public Ground(MainSystem mainSystem) {
        super(mainSystem);

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

                    if (pos == -1) {
                        return;
                    }
                    
                    // add arguments
                    List<Integer> args = new ArrayList<>();
                    args.add(pos/17); args.add(pos%17);

                    // COMPLETE 타일타입 예외처리
                    if (selectMode.getTileType() == -1) { new ErrorWindow(); return; }
                    args.add(selectMode.getTileType());
                   
                    

                    boolean result = sendMessage(mainSystem, "PutTile", args);

                    if (result) {
                        b.setIcon(ImgStore.getInstance().getRail(selectMode.getTileType()));
                        b.setName("-1");
                    }
                    selectMode.initTileType();

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