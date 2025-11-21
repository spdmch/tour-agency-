package tour.system.menu;

import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    public void show() {
        while (true) {
            System.out.println("║  СИСТЕМА ТУРИСТИЧНИХ ПУТІВОК          ║");
            System.out.println("\n📋 Оберіть дію:");
            System.out.println("1. 👤 Увійти в наявний акаунт");
            System.out.println("2. ✨ Створити новий акаунт");
            System.out.println("3. 🔐 Увійти як адміністратор");
            System.out.println("0. 🚪 Вихід з програми");
            System.out.print("\n➤ Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    new LoginMenu().show();
                    break;

                case "2":
                    new RegistrationMenu().show();
                    break;

                case "3":
                    new AdminLoginMenu().show();
                    break;

                case "0":
                    System.out.println("\n═══════════════════════════════════════");
                    System.out.println("  Дякуємо за використання системи!");
                    System.out.println("  Гарного відпочинку! 🌴✈️");
                    return;
                default:
                    System.out.println("\n❌ Невірний вибір! Спробуйте ще раз.");
            }
        }
    }
}
