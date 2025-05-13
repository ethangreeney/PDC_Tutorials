// File: Task09_3/QuizMain.java
package Task09_3;

import javax.swing.SwingUtilities;

public class QuizMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                QuizModel model = new QuizModel();
                QuizVeiw view = new QuizVeiw(); // MathQuiz is now the View
                new QuizController(model, view); // Controller links them

                view.setVisible(true);
            }
        });
    }
}