// File: Task09_3/QuizController.java
package Task09_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QuizController {
    private QuizModel model;
    private QuizVeiw view; // MathQuiz instance is the View

    public QuizController(QuizModel model, QuizVeiw view) {
        this.model = model;
        this.view = view;

        // Attach listeners
        this.view.addLoginButtonListener(new LoginListener());

        // Handle window closing
        this.view.addFrameWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Save score if a user might have been playing
                if (model.getCurrentUsername() != null) {
                    model.saveScore();
                }
                model.closeDbConnection();
                view.dispose(); // Close the window
                System.exit(0); // Terminate the application
            }
        });
    }

    // --- Listener Inner Classes ---
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Login button clicked (Controller)");
            String username = view.getUsernameInput();
            String password = view.getPasswordInput();
            view.showLoginError(false); // Hide error first

            if (username.isEmpty() || password.isEmpty()) {
                view.showLoginError(true);
                return;
            }

            if (model.attemptLogin(username, password)) {
                model.generateNewQuestion();
                view.switchToQuizPanel(); // Prepare the quiz panel UI
                // Now that quiz panel is set up, add its listeners
                view.addNextButtonListener(new NextButtonListener());
                view.addQuitButtonListener(new QuitButtonListener());
                view.displayQuestion(model.getNum1(), model.getNum2());
                view.resetLoginFields();
            } else {
                view.showLoginError(true);
                view.resetLoginFields();
            }
        }
    }

    class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Next button clicked (Controller)");
            String userAnswer = view.getCalcSolutionInput();
            model.checkAnswer(userAnswer); // Model updates score
            model.generateNewQuestion();
            view.displayQuestion(model.getNum1(), model.getNum2());
            // view.clearSolutionField(); // displayQuestion already clears it
            System.out.println("Current score: " + model.getCurrentScore());
        }
    }

    class QuitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Quit button clicked (Controller)");
            // Optional: check current answer before quitting if text field is not empty
            // String userAnswer = view.getCalcSolutionInput();
            // if(userAnswer != null && !userAnswer.trim().isEmpty()){
            // model.checkAnswer(userAnswer);
            // }
            model.saveScore();
            view.switchToScorePanel(model.getCurrentScore(), model.getCurrentUsername());
        }
    }
}