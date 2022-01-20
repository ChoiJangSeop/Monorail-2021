package gui;

import javax.swing.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.*;

public abstract class ControlButton {

    AsynchronousSocketChannel socketChannel;
    Mode impMode;

    public final String myAction;
    public abstract JButton getButton();
    public abstract JButton getButton(int posx, int posy);


    // TODO: 빌드패턴으로 변경
    public ControlButton(String myAction, AsynchronousSocketChannel socketChannel) {
        this.myAction = myAction;
        this.socketChannel = socketChannel;
    }

    public JButton getSubButton(int idx) {
        return new JButton();
    }
    public JButton getSubButton() {
        return new JButton();
    }

    void send(String data) {

        if (Mode.getInstance().getLock()) { return; }

        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = charset.encode(data);

        socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {}

            @Override
            public void failed(Throwable exc, Void attachment) {}
        });
    }

    public abstract void handleResult(List<Integer> args);

}