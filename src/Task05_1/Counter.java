package Task05_1;

public class Counter implements Runnable {

    int num;

    Counter(int i) {
        num = i;
    }

    public static void main(String[] args) {
        Thread PrintOdd = new Thread(new Counter(1));
        Thread PrintEven = new Thread(new Counter(2));
        PrintOdd.start();
        try {
            PrintOdd.join(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        PrintEven.start();
    }

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