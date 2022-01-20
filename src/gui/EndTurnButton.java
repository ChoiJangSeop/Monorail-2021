package gui;

import javax.swing.*;

import java.awt.Dimension;
import java.awt.event.*;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.List;

public class EndTurnButton extends ControlButton {

    private JButton endTurnButton = new JButton();
    private JButton turnPlayerButton = new JButton();
    private int curPlayer = 0;

    public EndTurnButton(AsynchronousSocketChannel socketChannel) {
        super("EndTurn", socketChannel);

        endTurnButton.setPreferredSize(new Dimension(150, 150));
        endTurnButton.setIcon(ImgStore.getInstance().getImg("CompleteImg"));
        turnPlayerButton.setIcon(ImgStore.getInstance().getImg("P1Img"));
        endTurnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = myAction;
                send(message);

                // TODO: handling imp mode
                /*
                if (!impMode.getImp()) { result = sendMessage(mainSystem, "TurnEnd"); }
                else { result = sendMessage(mainSystem, "GameEnd"); }
                */

            }
        });
    }

    @Override
    public void handleResult(List<Integer> args) {
        curPlayer = (curPlayer + 1) % 2;

        if (Mode.getInstance().getLock()) { Mode.getInstance().openLock(); }
        else { Mode.getInstance().closeLock(); }

        turnPlayerButton.setIcon(
                (curPlayer == 0) ?
                        ImgStore.getInstance().getImg("P1Img") :
                        ImgStore.getInstance().getImg("P2Img")
        );
    }

    @Override
    public JButton getButton() { return this.endTurnButton; }

    @Override public JButton getSubButton() {
        return this.turnPlayerButton;
    }
    @Override
    public JButton getButton(int posx, int posy) { return this.endTurnButton; }
}