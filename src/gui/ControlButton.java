package gui;

import javax.swing.*;
import java.util.*;

import system.MainSystem;

public interface ControlButton {
    public JButton getButton();
    public JButton getButton(int posx, int posy);
    
    public default JButton getSubButton(int idx) {
        return new JButton();
    }
    public default JButton getSubButton() {
        return new JButton();
    }

    public default void sendMessage(String message) {
        MainSystem.getInstance().userBeginAction(message);
    }
    

    public default void sendMessage(String message, List<Integer> args) {
        MainSystem.getInstance().userBeginAction(message, args);
    }
}