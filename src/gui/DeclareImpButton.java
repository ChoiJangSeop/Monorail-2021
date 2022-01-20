package gui;

import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.channels.AsynchronousSocketChannel;

public class DeclareImpButton extends ControlButton {

    private JButton declareImpButton = new JButton();

    public DeclareImpButton(AsynchronousSocketChannel socketChannel) {
        super("DeclareImp", socketChannel);

        declareImpButton.setPreferredSize(new Dimension(150, 150));
        declareImpButton.setIcon(ImgStore.getInstance().getImg("ImpossibleImg"));
        declareImpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton)e.getSource();

                /*
                boolean result = sendMessage(mainSystem, "TurnEnd");

                // result == true => 유저가 타일을 사용했다는 이야기! (불가능선언시에는 오류)
                if (result) {
                    // TODO pop up the error window (cannot declare impossible)
                    
                } else {
                    b.setEnabled(false);
                    impMode.goImp();
                    // BUG no pop up zeroTile Error
                    // TODO change turn 
                }
                */

            }


        });
    }

    @Override
    public void handleResult(List<Integer> args) {}

    @Override
    public JButton getButton() { return this.declareImpButton; }

    @Override
    public JButton getButton(int posx, int posy) { return this.declareImpButton; }
}