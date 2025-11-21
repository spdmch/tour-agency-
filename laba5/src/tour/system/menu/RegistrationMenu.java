package tour.system.menu;

import tour.system.entity.User;
import tour.system.util.FileManager;
import java.util.List;
import java.util.Scanner;

public class RegistrationMenu {
    private Scanner scanner = new Scanner(System.in);

    public void show() {
        System.out.println("║  РЕЄСТРАЦІЯ НОВОГО КОРИСТУВАЧА        ║");
        System.out.print("📧 Придумайте логін: ");
        String login = scanner.nextLine().trim();

        List<User> users = FileManager.loadUsers();
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                System.out.println("\n❌ Користувач з таким логіном вже існує!");
                System.out.println("💡 Підказка: спробуйте інший логін");
                return;
            }
        }
        System.out.print("🔒 Придумайте пароль: ");
        String password = scanner.nextLine().trim();
        System.out.print("👤 Ваше ПІБ: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("📱 Телефон: ");
        String phone = scanner.nextLine().trim();
        System.out.print("✉️  Email: ");
        String email = scanner.nextLine().trim();
        User newUser = new User(login, password, fullName, phone, email);
        FileManager.saveUser(newUser);

        System.out.println("║  ✅ РЕЄСТРАЦІЯ УСПІШНА!               ║");
        System.out.println("Тепер ви можете увійти в систему");
        System.out.println("використовуючи ваш логін: " + login);
    }
}
