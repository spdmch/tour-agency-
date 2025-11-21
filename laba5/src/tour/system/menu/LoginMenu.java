package tour.system.menu;

import tour.system.entity.User;
import tour.system.util.FileManager;
import java.util.List;
import java.util.Scanner;

public class LoginMenu {
    private Scanner scanner = new Scanner(System.in);
    public void show() {
        System.out.println("║  ВХІД В СИСТЕМУ                       ║");
        System.out.print("📧 Логін: ");
        String login = scanner.nextLine().trim();

        System.out.print("🔒 Пароль: ");
        String password = scanner.nextLine().trim();

        List<User> users = FileManager.loadUsers();
        User foundUser = null;

        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                foundUser = user;
                break;
            }
        }

        if (foundUser != null) {
            System.out.println("\n✅ Успішний вхід!");
            System.out.println("👋 Вітаємо, " + foundUser.getFullName() + "!");

            new ClientMenu(foundUser).show();
        } else {
            System.out.println("\n❌ Невірний логін або пароль!");
        }
    }
}
