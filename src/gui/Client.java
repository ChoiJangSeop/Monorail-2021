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
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import board.Board;

public class Client extends JFrame {

    private String name;
    private int id;

    private AsynchronousChannelGroup channelGroup;
    private AsynchronousSocketChannel socketChannel;

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

            ground = new Ground(socketChannel);
            endTurnButton = new EndTurnButton(socketChannel);
            declareImpButton = new DeclareImpButton(socketChannel);

            socketChannel.connect(new InetSocketAddress("localhost", 5001), name, new CompletionHandler<Void, String>() {
                @Override
                public void completed(Void result, String attachment) {
                    // COMPLETE: pop up the connect success windows
                    try { Thread.sleep(1000); }
                    catch (InterruptedException e) {}

                    new AlertWindow("alert", "연결이 되었습니다!");
                    receive();
                }

                @Override
                public void failed(Throwable exc, String attachment) {
                    // TODO: handle Exception
                    new AlertWindow("error", "서버와의 연결에 실패했습니다");
                    stopClient();
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

    void receive() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(100);

        socketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                attachment.flip();
                Charset charset = Charset.forName("UTF-8");
                String message = charset.decode(attachment).toString();
                System.out.println("[응답 받음] " + message);

                String[] messageList = message.split(" ");
                try {
                    if (messageList.length < 2) { throw new IOException(); }

                    String flag = messageList[0];
                    String command = messageList[1];

                    if (flag.equals("TRUE")) {
                        List<Integer> args = new ArrayList<>();
                        for (int i = 2; i < messageList.length; ++i) {
                            int arg = Integer.parseInt(messageList[i]);
                            args.add(arg);
                        }

                        switch (command) {
                            case "PutTile":
                                ground.handleResult(args);
                                break;
                            case "EndTurn":
                                endTurnButton.handleResult(args);
                                break;
                            case "DeclareImp":
                                declareImpButton.handleResult(args);
                                break;
                            case "Wait":
                                new AlertWindow("alert", "상대 플레이어를 기다리는 중입니다");
                                break;
                            case "BeginGame":
                                new AlertWindow("alert", "게임을 시작합니다");
                                id = args.get(0);
                                if (id == 1) { Mode.getInstance().openLock(); }
                                break;

                            default:
                                System.out.println("[잘못된 커멘드입니다.]");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("[서버에서 보낸 정보가 부족합니다] " + message);
                }
                ByteBuffer _byteBuffer = ByteBuffer.allocate(100);
                socketChannel.read(_byteBuffer, _byteBuffer, this);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                // TODO: handle exception
            }
        });
    }


    public Client() {

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