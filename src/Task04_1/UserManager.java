package Task04_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UserManager {

    HashMap<String, Integer> users = new HashMap<String, Integer>();

    private int score;

    String username;

    UserManager() {
        loadUsers();
    }

    private void loadUsers() {

        try (BufferedReader reader = new BufferedReader(new FileReader("resources/T04_users.txt"))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] userInfo = currentLine.split(" ");
                users.put(userInfo[0], Integer.parseInt(userInfo[1]));
            }
        } catch (IOException e) {
            System.out.println("Error loading file... Please ensure target file is in 'resoures/T04_users.txt'");
        }

        Scanner scan = new Scanner(System.in);

        System.out.println("What is your name?");
        username = scan.nextLine().toLowerCase();

        if (users.containsKey(username)) {
            System.out.println("User detected! user current score is " + users.get(username));
            score = users.get(username);
        } else {
            System.out.println("User dosnt exist, score starts at 0");
            score = 0;
        }
    }

    public void saveScores() {
        users.put(username, score);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/T04_users.txt"))) {
            for (String s : users.keySet()) {
                writer.write(s + " " + users.get(s));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to write to file");
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }
}
