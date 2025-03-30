package Task05_2;

public class PrintStars implements Runnable {

    int row;

    PrintStars(int noOfRows) {
        row = noOfRows;
    }

    public static void main(String[] args) {

        Thread PrintSpaces = new Thread(new PrintSpaces(9));
        Thread PrintStars = new Thread(new PrintStars(9));

        PrintSpaces.start();

        try {
            PrintSpaces.join(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PrintStars.start();

    }

    public void run() {

        for (int i = 1; i <= row; i++) {

            // Print stars
            for (int j = 0; j < (2 * i - 1); j++) {
                System.out.print("*");
            }
            System.out.println();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}

class PrintSpaces implements Runnable {
    int row;

    PrintSpaces(int noOfRows) {
        row = noOfRows;
    }

    public void run() {
        for (int i = 0; i < row; i++) {

            for (int j = 0; j < (row - i); j++) {
                System.out.print(" ");
            }
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}