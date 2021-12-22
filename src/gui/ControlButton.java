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

    public default boolean sendMessage(String message) {
        return MainSystem.getInstance().userBeginAction(message);
    }
    

    public default boolean sendMessage(String message, List<Integer> args) {
        return MainSystem.getInstance().userBeginAction(message, args);
    }
}