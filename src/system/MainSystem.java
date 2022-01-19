package system;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.Executors;

import board.Board;

import gui.EndWindow;
public class MainSystem {
    public static enum State { 
        NONE,
        TURN_END, 
        GAME_END, 
        IMP_MODE,
        TILE_CONNECT_ERROR,
        TILE_DIRECTION_ERROR,
        RAIL_CONNECT_ERROR,  
        NO_TILE_ERROR,
        ZERO_TILE_ERROR,
        OVER_TILE_ERROR  
    };

    private AsynchronousChannelGroup channelGroup;
    private AsynchronousServerSocketChannel serverSocketChannel;
    private List<Player> players = new ArrayList<>();
    private List<Client> connections = new Vector<>();

    int curPlayer = 0;
    private Lock lock = new Lock();

    public MainSystem() {
        try {
            channelGroup = AsynchronousChannelGroup.withFixedThreadPool(
                    Runtime.getRuntime().availableProcessors(),
                    Executors.defaultThreadFactory()
            );
            serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup);
            serverSocketChannel.bind(new InetSocketAddress("localhost", 5001));
        } catch (Exception e) {
            // TODO: handling exception for connections
        }

        System.out.println("[서버 시작]");

        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, String>() {
            @Override
            public void completed(AsynchronousSocketChannel socketChannel, String attachment) {
                try {
                    String message = "[연결 수락] " + socketChannel.getRemoteAddress() + " : " + Thread.currentThread().getName();
                    System.out.println(message);
                } catch (Exception e) {}

                Player player = new Player(attachment);
                Client client = new Client(socketChannel);
                players.add(player);
                connections.add(client);
                System.out.println("[연결된 플레이어수] : " + players.size());

                serverSocketChannel.accept(null, this);
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                // TODO: handling exception for connections
            }

        });
    }

    private class Client {
        private AsynchronousSocketChannel socketChannel;

        public Client(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
            receive();
        }

        private void receive() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(100);

            socketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    attachment.flip();
                    Charset charset = Charset.forName("UTF-8");
                    String message = charset.decode(attachment).toString();
                    String[] messageList = message.split(" ");

                    String command = messageList[0];

                    try {
                        System.out.println("[요청 처리 RECEIVE] " + message + " - " + socketChannel.getRemoteAddress());
                    } catch (Exception e) {}

                    boolean ret;
                    if (messageList.length > 1) {
                        List<Integer> args = new ArrayList<>();
                        for (int i=1; i<messageList.length; ++i) {
                            int arg = Integer.parseInt(messageList[i]);
                            args.add(arg);
                        }
                        ret = userBeginAction(command, args);
                    } else {
                        ret = userBeginAction(command);
                    }

                    String sendData = (ret ? "TRUE" : "FALSE") + " " + message + " " + curPlayer;
                    try {
                        System.out.println("[요청 처리 SEND] " + sendData + " - " + socketChannel.getRemoteAddress());
                    } catch (Exception e) {}
                    for (Client client : connections) {

                        // TODO : 접근권한 데이터까지 보내기
                        client.send(sendData);
                    }

                    ByteBuffer _byteBuffer = ByteBuffer.allocate(100);
                    socketChannel.read(_byteBuffer, _byteBuffer, this);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        System.out.println("[클라이언트 통신 안됨] " + socketChannel.getRemoteAddress() + Thread.currentThread().getName());
                        connections.remove(Client.this);
                        socketChannel.close();
                    } catch (IOException e) {}
                }
            });
        }

        private void send(String data) {
            Charset charset = Charset.forName("UTF-8");
            ByteBuffer byteBuffer = charset.encode(data);

            socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>() {
                @Override
                public void completed(Integer result, Void attachment) {}

                @Override
                public void failed(Throwable exc, Void attachment) {}
            });
        }
    }

    private void endAction(MainSystem.State state) {

        switch (state) {
            case NONE :
                // TODO: LOCK 권한 변경
                lock.openLock();
                break;

            case TURN_END :
                // TODO : LOCK 접근 관한 변경
                curPlayer = (curPlayer + 1) % 2;
                Board.getInstance().initUseTile();
                lock.openLock();
                break;
            
            case IMP_MODE :
                curPlayer = (curPlayer + 1) % 2;
                lock.openLock();
                break;

            case GAME_END :
                // TODO: send endwindows to client
                new EndWindow(curPlayer + 1);
                break;

            case ZERO_TILE_ERROR :
                beginAction("HandleError", "ZeroTileError");
                break;
                
            case NO_TILE_ERROR :
                beginAction("HandleError", "NoTileError");
                break;

            case OVER_TILE_ERROR :
                beginAction("HandleError", "OverTileError");
                break;
            
            case TILE_CONNECT_ERROR :
                beginAction("HandleError", "TileConnectError");
                break;
            
            case RAIL_CONNECT_ERROR :
                beginAction("HandleError", "RailConnectError");
                break;
            
            case TILE_DIRECTION_ERROR :
                beginAction("HandleError", "TileDirectionError");
                break;
            
        }
       

    }

    public boolean userBeginAction(String message, List<Integer> args) {

        // TODO: receive action to user
        if (lock.getLock()) {
            lock.closeLock();
            PlayTrigger playTrigger = new PlayTrigger();
            State endState = playTrigger.trig(players.get(curPlayer), message, args);
            System.out.println("[처리 결과] " + endState);
            boolean ret;

            // BUG: 잘못 타일 놓은 위치에 다시 타일 놓으면 무조건 에러뜸
            if (endState == State.NONE || endState == State.TURN_END || endState == State.GAME_END) { ret = true; } 
            else { ret = false; }

            endAction(endState);
            return ret;
        }
        
        return false;
    }

    public boolean userBeginAction(String message) {
        
        if (lock.getLock()) {
            lock.closeLock();
            PlayTrigger playTrigger = new PlayTrigger();
            State endState = playTrigger.trig(players.get(curPlayer), message);
            System.out.println("[처리 결과] " + endState);

            boolean ret;

            if (endState == State.NONE || endState == State.TURN_END || endState == State.GAME_END) { ret = true; } 
            else { ret = false; }

            endAction(endState);


            return ret;
        }

        return false;
    }

    // no user input
    public void beginAction(String message, String arg) {
        System.out.println("[내부 요청 처리] " + message + " : " + arg);

        PlayTrigger playTrigger = new PlayTrigger();
        State endState = playTrigger.trig(players.get(curPlayer), message, arg);
        System.out.println("[처리 결과] " + endState);
        endAction(endState);

    }

    // needs user input
    public void beginAction(String message) {
        PlayTrigger playTrigger = new PlayTrigger();
        State endState = playTrigger.trig(players.get(curPlayer), message);
        endAction(endState);
    }

    public static void main(String[] args) {
        Board.getInstance().initBoard();
        new MainSystem();
    }
}