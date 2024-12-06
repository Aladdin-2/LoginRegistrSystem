package model;

public class User {
    private String firstName;
    private String lastName;
    private String country;
    private String userName;
    private final String email;
    private String password;
    private String phoneNumber;
    private int age;


    public User(String firstName, String lastName, String country, String email, String phoneNumber, int age, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.country = country;
        this.email = validateEmail(email);
        this.password = validatePassword(password);
        this.phoneNumber = phoneNumber;
        this.age = age;
    }


    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    private String validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email.matches(emailRegex)) {
            return email;
        } else {
            throw new IllegalArgumentException("Invalid email format!");
        }
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        if (password != null) {
            return password.replace("*/-=", "");
        }
        return null;
    }

    public void setPassword(String password) {
        this.password = validatePassword(password);
    }

    public String validatePassword(String password) {
        if (password != null) {
            return password + "*/-=";
        } else {
            return null;
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return firstName + " , " + lastName + ", " + country + " , " + email + " , " + phoneNumber + " , " + age + " , " + userName + " , " + password;
    }
}
