package gui;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;

public class EndTurnButton implements ControlButton {

    private JButton endTurnButton = new JButton();
    private JButton turnPlayerButton = new JButton();
    private int curPlayer = 0;

    public EndTurnButton() {
        super();
        endTurnButton.setPreferredSize(new Dimension(150, 150));
        endTurnButton.setIcon(ImgStore.getInstance().getImg("CompleteImg"));
        turnPlayerButton.setIcon(ImgStore.getInstance().getImg("P1Img"));
        endTurnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 메세지를 메인gui로 보내기
                curPlayer = (curPlayer + 1) % 2;
                
                turnPlayerButton.setIcon(
                    (curPlayer == 0) ?
                    ImgStore.getInstance().getImg("P1Img") :
                    ImgStore.getInstance().getImg("P2Img")
                );

                boolean result = sendMessage("TurnEnd");

                if (!result) {
                    curPlayer = (curPlayer + 1) % 2;
                
                    turnPlayerButton.setIcon(
                        (curPlayer == 0) ?
                        ImgStore.getInstance().getImg("P1Img") :
                        ImgStore.getInstance().getImg("P2Img")
                    );
                }

            }
        });
    }

    @Override
    public JButton getButton() { return this.endTurnButton; }

    @Override public JButton getSubButton() {
        return this.turnPlayerButton;
    }
    @Override
    public JButton getButton(int posx, int posy) { return this.endTurnButton; }
}