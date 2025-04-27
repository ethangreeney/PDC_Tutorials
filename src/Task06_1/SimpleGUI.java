package Task06_1;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class SimpleGUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("My Frame");
        BGPanel panel = new BGPanel();

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JTextField textField = new JTextField();
        frame.add(textField);

    }

}
