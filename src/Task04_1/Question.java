package Task04_1;

import java.util.Random;

public class Question {

    int num1 = generateNumber(100);
    int num2 = generateNumber(100);
    String operation;
    double result;

    public void newQuestion() {
        switch (generateNumber(4)) {
            case 0:
                operation = "+";
                result = num1 + num2;
                break;
            case 1:
                operation = "-";
                result = num1 - num2;
                break;
            case 2:
                operation = "*";
                result = num1 * num2;
                break;
            case 3:
                operation = "/";

                while (num2 == 0) {
                    num2 = generateNumber(100);
                }
                result = 1d * num1 / num2;
                break;
            default:
                break;
        }
    }

    public static int generateNumber(int range) {
        return (new Random()).nextInt(range);
    }

    public static int checkAnswer(double a, double b) {
        if (Math.abs(a - b) < .01) {
            System.out.println("Correct, +10 points");
            return 10;
        } else {
            System.out.println("Incorrect, -10 points");
            return -10;
        }

    }

    public String toString() {
        return num1 + " " + operation + " " + num2 + " = ";
    }

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

}
