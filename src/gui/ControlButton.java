package gui;

import javax.swing.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.*;

import system.MainSystem;

public abstract class ControlButton {

    AsynchronousSocketChannel socketChannel;
    ImpMode impMode;

    public final String myAction;
    public abstract JButton getButton();
    public abstract JButton getButton(int posx, int posy);

    public ControlButton(String myAction, AsynchronousSocketChannel socketChannel, ImpMode impMode) {
        this.myAction = myAction;
        this.socketChannel = socketChannel;
        this.impMode = impMode;
    }

    public void startButton() {
        // BUG: 여러개의 클래스에서 사용 불가 (readPendingException)
        receive();
    }

    public JButton getSubButton(int idx) {
        return new JButton();
    }
    public JButton getSubButton() {
        return new JButton();
    }

    // TODO: send message to server


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

                    if (myAction.equals(command) && flag.equals("TRUE")) {
                        List<Integer> args = new ArrayList<>();
                        for (int i = 2; i < messageList.length; ++i) {
                            int arg = Integer.parseInt(messageList[i]);
                            args.add(arg);
                        }
                        handleResult(args);
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

    void send(String data) {
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(data);

        socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                // TODO: 접근권한 관련 설정
            }

            @Override
            public void failed(Throwable exc, Void attachment) {}
        });
    }

    public abstract void handleResult(List<Integer> args);

    /*
    public boolean sendMessage(MainSystem mainSystem, String message) {
        return mainSystem.userBeginAction(message);
    }

    public boolean sendMessage(MainSystem mainSystem, String message, List<Integer> args) {
        return mainSystem.userBeginAction(message, args);
    }
    */
}