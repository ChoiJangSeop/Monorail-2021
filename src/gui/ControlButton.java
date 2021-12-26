package gui;

import javax.swing.*;
import java.util.*;

import system.MainSystem;

public abstract class ControlButton {

    MainSystem mainSystem;
    ImpMode impMode;

    public abstract JButton getButton();
    public abstract JButton getButton(int posx, int posy);

    public ControlButton(MainSystem mainSystem, ImpMode impMode) { 
        this.mainSystem = mainSystem; 
        this.impMode = impMode;
    }

    public JButton getSubButton(int idx) {
        return new JButton();
    }
    public JButton getSubButton() {
        return new JButton();
    }

    public boolean sendMessage(MainSystem mainSystem, String message) {
        return mainSystem.userBeginAction(message);
    }
    

    public boolean sendMessage(MainSystem mainSystem, String message, List<Integer> args) {
        return mainSystem.userBeginAction(message, args);
    }
}