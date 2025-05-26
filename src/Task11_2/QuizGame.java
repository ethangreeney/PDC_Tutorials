package Task11_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGame {

    public static void main(String[] args) {
        User user = new User();
        List<Question> questions = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        user.userName = scanner.nextLine();

        System.out.println("Hello " + user.userName + "! Welcome to the Math Quiz.");
        System.out.println("Enter your answer as a number. Press 'X' to quit.");

        for (int i = 0; i < 10; i++) {
            questions.add(new MathQuizQuestion());
        }

        int questionNumber = 1;
        for (Question question : questions) {
            System.out.println("\nQuestion " + questionNumber++ + ":");
            question.printQuestion();
            String answer;
            do {
                answer = scanner.nextLine();
            } while (!isValidInput(answer));

            if (answer.trim().equalsIgnoreCase("x")) {
                System.out.println("You chose to quit.");
                break;
            }

            if (question.checkAnswer(answer)) {
                user.score += question.getQuestionScore();
                System.out.println("Correct! You get " + question.getQuestionScore() + " points!");
            } else {
                user.score -= question.getQuestionScore();
                System.out.println("Wrong! Correct answer was: " + question.getQuestionAnswer() + ". You lose "
                        + question.getQuestionScore() + " points.");
            }
        }

        System.out.println("\n--- Quiz Finished ---");
        System.out.println(user.userName + ", your final score is: " + user.score);

        System.out.println("\n--- Review of All Questions ---");
        for (Question q : questions) {
            q.printQuestionInfo();
        }
        scanner.close();
    }

    public static boolean isValidInput(String answer) {

        if (answer == null)
            return false;

        String trimmedAnswer = answer.trim();

        if (trimmedAnswer.equalsIgnoreCase("x")) {
            return true;
        }

        try {
            Double.parseDouble(trimmedAnswer);
            return true;
        } catch (Exception e) {
        }

        System.out.print("Invalid input. Please enter a number or 'X' to quit: ");
        return false;
    }
}