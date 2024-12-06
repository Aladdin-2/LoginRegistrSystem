package service;

import model.User;
import repository.UserRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private final Scanner scan = new Scanner(System.in);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void registeredUser() throws InterruptedException {
        System.out.println("Write the first name!");
        String firstName = scan.nextLine();
        System.out.println("Write the last name!");
        String lastName = scan.nextLine();
        System.out.println("Which country? ");
        String country = scan.nextLine();
        System.out.println("Your email!");
        String email = scan.nextLine();
        if (!isEmailRegistered(email)) {
            System.out.println("Your age!");
            int age = scan.nextInt();
            scan.nextLine();
            System.out.println("Your phone number!");
            String phoneNumber = scan.nextLine();
            System.out.println("To complete registration!");
            Thread.sleep(100);
            System.out.println("User name!");
            String userName = scan.nextLine();
            System.out.println("User password!");
            String password = scan.nextLine();
            User user = new User(firstName, lastName, country, email, phoneNumber, age, userName, password);
            userRepository.users.add(user);
            userRepository.writeToFile(user);
        }
    }

    public void loginUser() {
        System.out.println("Your user name!");
        String username = scan.nextLine();
        System.out.println("Your password!");
        String password = scan.nextLine();

        if (username != null && password != null) {

            User user = verifyUserCredentials(username, password);
            if (user != null) {
                System.out.println("Login successfully!");
            } else {
                System.out.println("User name or password INCORRECT!");
            }
        }
    }


    public User verifyUserCredentials(String userName, String password) {
        if (!userRepository.getUsers().isEmpty()) {
            for (User user : userRepository.getUsers()) {
                boolean checkUserName = userName != null && user.getUserName().equals(userName);
                boolean checkPassword = password != null && user.getPassword().equals(password);
                if (checkPassword && checkUserName) {
                    return user;
                }
            }
        }
        return null;
    }

    public Boolean isEmailRegistered(String email) {
        List<User> users = userRepository.getUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("A user with this email address already exists!");
                return true;
            }
        }
        return false;
    }


    public void getUserDetails() {
        System.out.println("Your username!");
        String userName = scan.nextLine();
        if (!userRepository.getUsers().isEmpty()) {
            for (User user : userRepository.getUsers()) {
                if (user.getUserName().equals(userName)) {
                    System.out.println(user);
                }
            }
        }
        System.out.println("this user is not on the list!");
    }


    public void showUsers() {

        if (!userRepository.getUsers().isEmpty()) {
            for (User user : userRepository.users) {
                System.out.println(user);
            }
        } else {
            System.out.println("List is empty");
        }
    }


    public void showMainMenu() throws InterruptedException {
        int countChoice = 0;

        while (countChoice < 3) {
            try {
                System.out.println("1.Registration user!");
                System.out.println("2.Login user!");
                System.out.println("3.User details!");
                System.out.println("4.Exit..");
                int choice = scan.nextInt();
                scan.nextLine();
                switch (choice) {
                    case 1:
                        registeredUser();
                        break;
                    case 2:
                        loginUser();
                        break;
                    case 3:
                        getUserDetails();
                        break;
                    case 4:
                        System.out.println("Exiting the application...");
                        return;
                    default:
                        System.out.println("Invalid choice! Please select between 1 and 4.");
                        countChoice++;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scan.nextLine();
                countChoice++;
            }
            if (countChoice >= 3) {
                System.out.println("Too many invalid attempts. Exiting...");
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserRepository userRepository1 = new UserRepository();
        UserService userService = new UserService(userRepository1);

        // userService.registeredUser();
        //    userService.showUsers();
        userService.loginUser();
    }

}