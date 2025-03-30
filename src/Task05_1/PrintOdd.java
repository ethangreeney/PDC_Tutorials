package Task05_1;

public class PrintOdd implements Runnable {

    int num = 1;

    public static void main(String[] args) {
        Thread PrintOdd = new Thread(new PrintOdd());
        Thread PrintEven = new Thread(new PrintEven());
        PrintOdd.start();
        PrintEven.start();
    }

    public void run() {
        for (int j = this.num; j <= 10; j += 2) {
            System.out.print(j + " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PrintEven implements Runnable {

    int num = 2;

    public void run() {
        for (int j = num; j <= 10; j += 2) {
            System.out.print(j + " ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}