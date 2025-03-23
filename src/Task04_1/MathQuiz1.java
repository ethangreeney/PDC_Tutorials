package Task04_1;

import java.util.Scanner;

/**
 *
 * @author Shiqing Wu
 */

public class MathQuiz1 {

    public static void main(String[] args) {

        int score = 0;
        int rounds = 3;
        Scanner sc = new Scanner(System.in);

        while (rounds > 0) {

            Question thisQ = new Question();
            thisQ.newQuestion();
            double cAnswer = thisQ.getResult();
            double uAnswer = Double.MAX_VALUE;
            boolean isValid = false;

            while (!isValid) {
                System.out.println(thisQ);
                System.out.print("Your answer is:  (x to quit) ");
                String line = sc.nextLine();
                if (line.trim().equalsIgnoreCase("x")) {
                    System.out.println("You got: " + score);
                    System.exit(0);
                }
                try {
                    uAnswer = Double.parseDouble(line);
                    isValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please input again.");
                }
            }

            score += Question.checkAnswer(uAnswer, cAnswer);
            rounds--;
        }
        System.out.println("You got: " + score);

        sc.close();
    }

}
