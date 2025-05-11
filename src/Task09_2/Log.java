package Task09_2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    BufferedWriter bufWriter;
    public static int instanceNumber = 0;
    private static Log thisLog;

    public static Log getInstance() {
        if (thisLog == null) {
            thisLog = new Log();
        }
        return thisLog;
    }

    public void addToLog(String str) {
        try {
            this.bufWriter = new BufferedWriter(new FileWriter("./resources/T09_log.txt", true));

            System.out.println("writting log");
            bufWriter.write(str);
            bufWriter.flush();

        } catch (IOException e) {
        }
        instanceNumber++;
        System.out.println("A log instance has been created");
    }
}
