package gui;

import java.awt.event.*;
import javax.swing.*;


public class SelectMode extends JFrame {

    private JButton[] TileOption = new JButton[6];
    private int tileType = -1;

    public SelectMode() {
    
        
        for(int i=0;i<6;i++) {
            TileOption[i] = new JButton();
            TileOption[i].setIcon(ImgStore.getInstance().getRail(i));
            TileOption[i].setName(""+i);
            
            //새로운 창에서 버튼을 눌렀을 때 새로운창 꺼지고 nexticon 값 변화 
            
            TileOption[i].addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    JButton b = (JButton)e.getSource();
                    int name = Integer.parseInt(b.getName());
                    tileType = name;
                }
            });
            
        }
    }

    public JButton getButton(int i) { return this.TileOption[i]; }
    public void initTileType() { this.tileType = -1; }
    public int getTileType() { return this.tileType; }
    
}