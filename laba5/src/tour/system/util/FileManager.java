package tour.system.util;

import tour.system.entity.*;
import java.io.*;
import java.util.*;

public class FileManager {
    private static final String USERS_FILE = "users.txt";
    private static final String ADMINS_FILE = "admins.txt";
    private static final String TOURS_FILE = "tours.txt";
    private static final String BOOKINGS_FILE = "bookings.txt";

    public static void initializeFiles() {
        createFileIfNotExists(USERS_FILE);
        createFileIfNotExists(BOOKINGS_FILE);
        if (!new File(ADMINS_FILE).exists()) {
            createDefaultAdmins();
        }

        if (!new File(TOURS_FILE).exists()) {
            createDefaultTours();
        }
    }
    private static void createFileIfNotExists(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("✅ Створено файл: " + filename);
            } catch (IOException e) {
                System.err.println("❌ Помилка створення файлу " + filename + ": " + e.getMessage());
            }
        }
    }

    private static void createDefaultAdmins() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ADMINS_FILE))) {
            writer.println("admin|admin123");
            writer.println("manager|manager123");
            System.out.println("✅ Створено адміністраторів за замовчуванням");
            System.out.println("   • Логін: admin, Пароль: admin123");
            System.out.println("   • Логін: manager, Пароль: manager123");
        } catch (IOException e) {
            System.err.println("❌ Помилка створення адміністраторів: " + e.getMessage());
        }
    }

    private static void createDefaultTours() {
        List<Tour> tours = Arrays.asList(
                new Tour("T001", "Відпочинок на Мальдівах", "відпочинок", "Мальдіви", "Мале",
                        "2025-06-15", 7, "літак", "готель", 5, "AI", 45000.0,
                        "Дайвінг, Екскурсія островами, Романтична вечеря"),

                new Tour("T002", "Шопінг в Мілані", "шопінг", "Італія", "Мілан",
                        "2025-05-20", 4, "літак", "готель", 4, "BB", 25000.0,
                        "Тур торговими центрами, Екскурсія містом, Відвідування аутлетів"),

                new Tour("T003", "Лікування в Трускавці", "лікування", "Україна", "Трускавець",
                        "2025-04-10", 14, "автобус", "пансіон", 3, "FB", 18000.0,
                        "Мінеральні води, Лікувальні процедури, Масаж, SPA"),

                new Tour("T004", "Круїз Середземним морем", "круїз", "Середземномор'я", "Барселона",
                        "2025-07-01", 10, "лайнер", "готель", 5, "AI", 65000.0,
                        "Візити до 7 портів, Розважальні шоу, Екскурсії у кожному порту"),

                new Tour("T005", "Екскурсія по Парижу", "екскурсія", "Франція", "Париж",
                        "2025-05-05", 5, "поїзд", "готель", 3, "BB", 22000.0,
                        "Ейфелева вежа, Лувр, Версаль, Нотр-Дам, Монмартр"),

                new Tour("T006", "Відпочинок в Карпатах", "відпочинок", "Україна", "Буковель",
                        "2025-12-20", 7, "автобус", "готель", 4, "HB", 15000.0,
                        "Гірськолижний курорт, Термальні басейни, Канатна дорога"),

                new Tour("T007", "Шопінг в Дубаї", "шопінг", "ОАЕ", "Дубай",
                        "2025-11-10", 6, "літак", "готель", 5, "BB", 38000.0,
                        "Dubai Mall, Gold Souk, Outlet Village, Burj Khalifa"),

                new Tour("T008", "Екскурсія по Єгипту", "екскурсія", "Єгипет", "Каїр",
                        "2025-03-15", 8, "літак", "готель", 4, "FB", 28000.0,
                        "Піраміди Гізи, Луксор, Круїз по Нілу, Храми Карнак"),

                new Tour("T009", "Лікування в Карлових Варах", "лікування", "Чехія", "Карлові Вари",
                        "2025-09-01", 12, "автобус", "пансіон", 4, "FB", 32000.0,
                        "Термальні джерела, SPA-процедури, Оздоровчі програми"),

                new Tour("T010", "Відпочинок в Туреччині", "відпочинок", "Туреччина", "Анталія",
                        "2025-08-10", 10, "літак", "готель", 5, "AI", 35000.0,
                        "Пляжний відпочинок, Аквапарк, Хамам, Анімація")
        );

        try (PrintWriter writer = new PrintWriter(new FileWriter(TOURS_FILE))) {
            for (Tour tour : tours) {
                writer.println(tour.toFileString());
            }
            System.out.println("✅ Створено базу турів (10 путівок)");
        } catch (IOException e) {
            System.err.println("❌ Помилка створення турів: " + e.getMessage());
        }
    }
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromFileString(line);
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Помилка читання користувачів: " + e.getMessage());
        }
        return users;
    }

    public static void saveUser(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE, true))) {
            writer.println(user.toFileString());
        } catch (IOException e) {
            System.err.println("❌ Помилка збереження користувача: " + e.getMessage());
        }
    }
    public static List<Admin> loadAdmins() {
        List<Admin> admins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMINS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Admin admin = Admin.fromFileString(line);
                if (admin != null) {
                    admins.add(admin);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Помилка читання адміністраторів: " + e.getMessage());
        }
        return admins;
    }

    public static List<Tour> loadTours() {
        List<Tour> tours = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TOURS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Tour tour = Tour.fromFileString(line);
                if (tour != null) {
                    tours.add(tour);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Помилка читання турів: " + e.getMessage());
        }
        return tours;
    }

    public static void saveTours(List<Tour> tours) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TOURS_FILE))) {
            for (Tour tour : tours) {
                writer.println(tour.toFileString());
            }
        } catch (IOException e) {
            System.err.println("❌ Помилка збереження турів: " + e.getMessage());
        }
    }

    public static void appendTour(Tour tour) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TOURS_FILE, true))) {
            writer.println(tour.toFileString());
        } catch (IOException e) {
            System.err.println("❌ Помилка додавання туру: " + e.getMessage());
        }
    }

    public static List<Booking> loadBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKINGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Booking booking = Booking.fromFileString(line);
                if (booking != null) {
                    bookings.add(booking);
                }
            }
        } catch (IOException e) {
            System.err.println("❌ Помилка читання бронювань: " + e.getMessage());
        }
        return bookings;
    }

    public static void appendBooking(Booking booking) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKINGS_FILE, true))) {
            writer.println(booking.toFileString());
        } catch (IOException e) {
            System.err.println("❌ Помилка збереження бронювання: " + e.getMessage());
        }
    }
}