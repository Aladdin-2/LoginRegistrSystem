import repository.UserRepository;
import service.UserService;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        userService.showMainMenu();
    }
}
