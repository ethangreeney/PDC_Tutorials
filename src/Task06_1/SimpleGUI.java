package Task06_1;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("PDC task");
        BGPanel panel = new BGPanel();

        Container pane = frame.getContentPane();

        frame.add(panel);
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel bottomMenu = new JPanel();

        JTextField textField = new JTextField(20);
        bottomMenu.add(textField);

        JButton updateButton = new JButton("Update");
        bottomMenu.add(updateButton);

        pane.add(bottomMenu, BorderLayout.SOUTH);

        JLabel userText = new JLabel("Welcome to my program, PDC is the best!");
        panel.add(userText);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userText.setText(textField.getText());

            }
        });

        frame.setVisible(true);
    }

}
