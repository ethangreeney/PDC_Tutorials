package Task09_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class QuizVeiw extends JFrame {

    private JPanel userPanel = new JPanel();
    private JPanel calcPanel = new JPanel();
    private JLabel uName = new JLabel("Username: ");
    private JLabel pWord = new JLabel("Password: ");
    private JTextField unInput = new JTextField(10);
    private JTextField pwInput = new JTextField(10);
    private JLabel wrongName = new JLabel("Wrong username or password!");

    private JLabel firstNumber = new JLabel();
    private JLabel secondNumber = new JLabel();
    private JLabel additionLabel = new JLabel("+");
    private JButton nextButton = new JButton("Next");
    private JButton quitButton = new JButton("Quit");
    private JButton loginButton = new JButton("Log in");

    private JTextField calcSolution = new JTextField(10);

    public QuizVeiw() {

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Controller will handle close
        this.setSize(600, 200);
        this.setTitle("Math Quiz - MVC");

        userPanel.add(uName);
        userPanel.add(unInput);
        userPanel.add(pWord);
        userPanel.add(pwInput);
        userPanel.add(loginButton);
        userPanel.add(wrongName); // Add wrongName label to panel
        wrongName.setVisible(false); // Initially hidden
        this.add(userPanel);

        // The ActionListener for loginButton will be added by the Controller
        // Removed original loginButton.addActionListener
    }

    // --- Methods for Controller to get input from View ---
    public String getUsernameInput() {
        return unInput.getText();
    }

    public String getPasswordInput() {
        return pwInput.getText();
    }

    public String getCalcSolutionInput() {
        return calcSolution.getText();
    }

    public void resetLoginFields() {
        unInput.setText("");
        pwInput.setText("");
    }

    // --- Methods for Controller to attach listeners ---
    public void addLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addNextButtonListener(ActionListener listener) {
        nextButton.addActionListener(listener);
    }

    public void addQuitButtonListener(ActionListener listener) {
        quitButton.addActionListener(listener);
    }

    public void addFrameWindowListener(java.awt.event.WindowListener listener) {
        this.addWindowListener(listener);
    }

    // --- Methods for Controller to update View's display ---
    public void showLoginError(boolean show) {
        wrongName.setVisible(show);
    }

    public void switchToQuizPanel() {
        // This method replaces the core logic of the old startQuiz()
        calcPanel.removeAll(); // Clear previous components if any
        calcPanel.add(firstNumber);
        calcPanel.add(additionLabel);
        calcPanel.add(secondNumber);
        calcPanel.add(calcSolution);
        calcPanel.add(nextButton);
        calcPanel.add(quitButton);
        // ActionListeners for nextButton and quitButton will be added by Controller
        // after this call

        this.getContentPane().removeAll();
        this.add(calcPanel);
        this.revalidate();
        this.repaint();
    }

    public void displayQuestion(int num1, int num2) {
        firstNumber.setText(num1 + "");
        secondNumber.setText(num2 + "="); // Kept the original format
        calcSolution.setText("");
        calcPanel.revalidate(); // Ensure panel updates if components change size/text
        calcPanel.repaint();
    }

    public void clearSolutionField() {
        calcSolution.setText("");
    }

    public void switchToScorePanel(int finalScore, String username) {
        // This method replaces the core logic of the old quitGame() UI part
        JPanel quitPanel = new JPanel();
        JLabel scoreLabel = new JLabel("User: " + username + ", Your score: " + finalScore);
        quitPanel.add(scoreLabel);
        this.getContentPane().removeAll();
        this.add(quitPanel);
        this.revalidate();
        this.repaint();
    }

    // Removed dbsetup(), checkTableExisting(), checkName(), startQuiz(),
    // newQuestion(), getNumber(), quitGame()
    // The logic from these methods is now in Model or Controller, or adapted into
    // the new View methods above.
}