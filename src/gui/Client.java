package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.Executors;

import board.Board;
import system.MainSystem;

public class Client extends JFrame {

    private String name;
    private AsynchronousChannelGroup channelGroup;
    private AsynchronousSocketChannel socketChannel;

    //
    private ImpMode impMode = new ImpMode();
    private JButton[] deco = new JButton[17];

    // control button
    private Ground ground;
    private EndTurnButton endTurnButton;
    private DeclareImpButton declareImpButton;


    void startClient() {
        try {
            channelGroup = AsynchronousChannelGroup.withFixedThreadPool(
                    Runtime.getRuntime().availableProcessors(),
                    Executors.defaultThreadFactory()
            );
            socketChannel = AsynchronousSocketChannel.open(channelGroup);

            ground = new Ground(socketChannel, impMode);
            endTurnButton = new EndTurnButton(socketChannel, impMode);
            declareImpButton = new DeclareImpButton(socketChannel, impMode);

            socketChannel.connect(new InetSocketAddress("localhost", 5001), name, new CompletionHandler<Void, String>() {
                @Override
                public void completed(Void result, String attachment) {
                    // TODO: pop up the connect success windows
                    // TODO: construct control button
                }

                @Override
                public void failed(Throwable exc, String attachment) {
                    // TODO: handle Exception
                }
            });
        } catch (Exception e) {
            if (socketChannel.isOpen()) { stopClient(); }
            return;
        }
    }

    void stopClient() {
        try {
            // TODO: pop up the stopClient windows
            if (channelGroup != null && !channelGroup.isShutdown()) { channelGroup.shutdownNow(); }
        } catch (IOException e1) {}
    }



    public Client() {

        startClient();

        setTitle("--MONORAIL GAME--");
        setDeco();
        setBackground(Color.green);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        
        for (int i=0; i<11; ++i) {
            for (int j=0; j<17; ++j) {
                add(ground.getButton(i, j));
            }
        }

        add(endTurnButton.getSubButton());
        
        JPanel selectGroup = new JPanel();
        selectGroup.setLayout(new GridLayout(2,3));
        selectGroup.setPreferredSize(new Dimension(150, 150));

        for (int i=0; i<6; ++i) {
            selectGroup.add(ground.getSubButton(i));
        }

        add(selectGroup);
        add(declareImpButton.getButton());   
        add(endTurnButton.getButton());
        
        setSize(950,900);
		setBackground(Color.black);
		setVisible(true);

        ground.startButton();
        //endTurnButton.startButton();
        //declareImpButton.startButton();
    }

    private void setDeco() {
        
		for (int i=0; i<17; i++) {
			deco[i] = new JButton();
			deco[i].setPreferredSize(new Dimension(50, 50));
			deco[i].setBackground(new Color(129, 193, 71));
			deco[i].setFont(new Font("consolas", Font.BOLD, 22));
			add(deco[i]);
		}
		
		deco[2].setText("M");
		deco[3].setText("O");
		deco[4].setText("N");
		deco[5].setText("O");
		deco[6].setText("R");
		deco[7].setText("A");
		deco[8].setText("I");
		deco[9].setText("L");
		deco[10].setText("-");
		deco[11].setText("G");
		deco[12].setText("A");
		deco[13].setText("M");
		deco[14].setText("E");
    }

    public static void main(String[] args) {
        Board.getInstance().initBoard();
        new Client();
    }

}