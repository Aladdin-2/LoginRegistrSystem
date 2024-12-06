package repository;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepository {
    private final static String USERS_FILE = "users.txt";
    public final List<User> users = new ArrayList<>();
    private boolean cache = true;

    public UserRepository() {
        refreshCache();
    }

    public void writeToFile(User user) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(USERS_FILE, true))) {

            bufferedWriter.write(user.toString());
            bufferedWriter.newLine();
            users.add(user);
            cache = false;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<User> readFromFile() {
        users.clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] details = line.split(",");
                String firstName = details[0].trim();
                String lastName = details[1].trim();
                String country = details[2].trim();
                String email = details[3].trim();
                String phoneNumber = details[4].trim();
                int age = Integer.parseInt(details[5].trim());
                String username = details[6].trim();
                String password = details[7].trim();
                String encryptPassword = password.substring(0, password.length() - 4);
                users.add(new User(firstName, lastName, country, email, phoneNumber, age, username, encryptPassword));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    private void refreshCache() {
        if (cache) {
            users.clear();
            users.addAll(readFromFile());
            cache = false;
        }
    }

    public List<User> getUsers() {
        refreshCache();
        return new ArrayList<>(users);
    }

}
