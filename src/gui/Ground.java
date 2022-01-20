package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.List;

public class Ground extends ControlButton {
    
    private JButton[] groundBtn = new JButton[187];
    private SelectMode selectMode = new SelectMode();

    public Ground(AsynchronousSocketChannel socketChannel) {
        super("PutTile", socketChannel);

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
                    int x = pos/17; int y = pos%17;

                    // COMPLETE 타일타입 예외처리
                    if (selectMode.getTileType() == -1) { new AlertWindow("error","타일의 종류를 선택해주세요"); return; }
                    int type = selectMode.getTileType();

                    // 서버에 보낼 메세지
                    String message = myAction + " " + x + " " + y + " " + type;
                    send(message);

                    // TODO: handle imp situation
                    /*
                    boolean result;
                    if (!impMode.getImp()) { result = sendMessage(mainSystem, "PutTile", args); }
                    else { result = sendMessage(mainSystem, "ImpPutTile", args); }
                    */
                }
            });
        }
    }

    public void handleResult(List<Integer> args) {
        System.out.println(args);
        int x = args.get(0);
        int y = args.get(1);
        int type = args.get(2);

        groundBtn[17*x + y].setIcon(ImgStore.getInstance().getRail(type));
        groundBtn[17*x + y].setName("-1");
        selectMode.initTileType();
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