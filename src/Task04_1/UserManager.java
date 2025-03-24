package Task04_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {

    HashMap<String, Integer> users = new HashMap<String, Integer>();

    UserManager() {
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("/resoures/T04_users.txt"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] userInfo = currentLine.split(" ");
                users.put(userInfo[0], Integer.parseInt(userInfo[1]));
            }
        } catch (IOException e) {
            System.out.println("Error loading file... Please ensure target file is in 'resoures/T04_users.txt'");
        }
    }

    public int setUser() {

        Scanner scan = new Scanner(System.in);

        System.out.println("What is your name?");
        String userName = scan.nextLine();

        if (users.containsKey(userName)) {
            System.out.println("User detected! user current score is" + users.get(userName));
            scan.close();
            return users.get(userName);
        } else {
            System.out.println("User dosnt exist, score starts at 0");
            scan.close();
            return 0;
        }

    }

}
