package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EndWindow extends JFrame {

    public EndWindow(int winner) {
        
        setTitle("Error!");
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label = new JLabel(winner + "P is Winner!", JLabel.CENTER);
        label.setFont(new Font("", Font.BOLD, 20));
        label.setPreferredSize(new Dimension(700, 50));
        add(label);

        JButton bt = new JButton("확인");

        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(bt);

        setSize(500, 200);
        this.setLocation(225, 275);
        setVisible(true);
    }
}