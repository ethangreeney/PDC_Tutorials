package Task11_2;

import java.util.Random;

public class MathQuizQuestion extends Question {

    private int num1;
    private int num2;
    private String mathOp;
    private double actualAnswerValue;

    public MathQuizQuestion() {

        super();

        Random rand = new Random();

        num1 = rand.nextInt(100);
        num2 = rand.nextInt(100);

        mathOp = generateOperator(rand);

        while (mathOp.equals("/") && num2 == 0) {
            num2 = rand.nextInt(100);
        }

        questionText = num1 + " " + mathOp + " " + num2 + " = ?";
        actualAnswerValue = calculateResult();
        questionAnswer = String.format("%.1f", actualAnswerValue);
        questionScore = 5;
    }

    private String generateOperator(Random rand) {

        String[] ops = { "+", "-", "*", "/" };
        return ops[rand.nextInt(ops.length)];

    }

    private double calculateResult() {

        switch (mathOp) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return (double) num1 / num2;
            default:
                return 0;
        }

    }

    public boolean checkAnswer(String userAnswer) {
        double userAnswerDouble = Double.parseDouble(userAnswer);
        double correctAnswerDouble = Double.parseDouble(questionAnswer.toString());
        return Math.abs(userAnswerDouble - correctAnswerDouble) < 0.001;
    }

    public void printQuestion() {
        System.out.println("Quiz Question: " + questionText);
        System.out.print("Your answer is: ");
    }

    public void printQuestionInfo() {
        System.out.println("\nMQQ Details");
        System.out.println("Question: " + questionText);
        System.out.println("Correct Answer: " + questionAnswer);
        System.out.println("Score: " + questionScore);
        System.out.println("Created by " + createdBy + ", on " + createdOn);
    }
}