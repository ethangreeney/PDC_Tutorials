package Task06_2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleGUI {

    public static void main(String[] args) {

        String[] myFontList = {
                "Arial",
                "Times New Roman",
                "Courier New",
                "Verdana",
                "Georgia",
                "Comic Sans MS",
                "Dialog",
                "Serif",
                "SansSerif"
        };

        Integer[] fontSizes = { 8, 10, 12, 14, 16, 18, 20, 24, 28, 32, 36, 48, 72 };

        Font userFont = new Font("Arial", Font.PLAIN, 16);

        JFrame frame = new JFrame("PDC task");
        BGPanel panel = new BGPanel();

        frame.setResizable(false);

        Container pane = frame.getContentPane();

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel bottomMenu = new JPanel();

        JTextField textField = new JTextField(20);
        bottomMenu.add(textField);

        JButton updateButton = new JButton("Update");
        bottomMenu.add(updateButton);

        pane.add(bottomMenu, BorderLayout.SOUTH);

        JPanel fontMenu = new JPanel();

        JLabel fontText = new JLabel("Font:");
        fontMenu.add(fontText);

        JComboBox<String> fontDropdown = new JComboBox<>(myFontList);
        fontMenu.add(fontDropdown);

        JLabel fontSizeText = new JLabel("Font Size:");
        fontMenu.add(fontSizeText);

        JComboBox<Integer> fontSizeDropdown = new JComboBox<>(fontSizes);
        fontSizeDropdown.setSelectedItem(fontSizes[4]);
        fontMenu.add(fontSizeDropdown);

        pane.add(fontMenu, BorderLayout.NORTH);

        JLabel userText = new JLabel("Welcome to my program, PDC is the best!");
        userText.setFont(userFont);
        panel.add(userText);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userText.setText(textField.getText());

            }
        });

        fontDropdown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedFont = (String) fontDropdown.getSelectedItem();
                int selectedFontSize = (Integer) fontSizeDropdown.getSelectedItem();

                userText.setFont(new Font(selectedFont, Font.PLAIN, selectedFontSize));
            }
        });

        fontSizeDropdown.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedFont = (String) fontDropdown.getSelectedItem();
                int selectedFontSize = (Integer) fontSizeDropdown.getSelectedItem();

                userText.setFont(new Font(selectedFont, Font.PLAIN, selectedFontSize));
            }

        });

        frame.setVisible(true);
    }

}
