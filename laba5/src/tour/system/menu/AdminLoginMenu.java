package tour.system.menu;

import tour.system.entity.Admin;
import tour.system.util.FileManager;
import java.util.List;
import java.util.Scanner;

public class AdminLoginMenu {
    private Scanner scanner = new Scanner(System.in);

    public void show() {
        System.out.println("║  🔐 ВХІД АДМІНІСТРАТОРА               ║");
        System.out.print("\n🆔 Логін адміністратора: ");
        String login = scanner.nextLine().trim();
        System.out.print("🔑 Пароль: ");
        String password = scanner.nextLine().trim();

        List<Admin> admins = FileManager.loadAdmins();
        Admin foundAdmin = null;

        for (Admin admin : admins) {
            if (admin.getLogin().equals(login) && admin.getPassword().equals(password)) {
                foundAdmin = admin;
                break;
            }
        }
        if (foundAdmin != null) {
            System.out.println("\n✅ Вхід адміністратора успішний!");
            new AdminMenu().show();
        } else {
            System.out.println("\n❌ ДОСТУП ЗАБОРОНЕНО!");
            System.out.println("⚠️  Невірний логін або пароль адміністратора");
        }
    }
}
