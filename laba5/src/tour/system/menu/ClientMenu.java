package tour.system.menu;
import tour.system.entity.*;
import tour.system.util.FileManager;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientMenu {
    private Scanner scanner = new Scanner(System.in);
    private User currentUser; // Поточний користувач

    public ClientMenu(User user) {
        this.currentUser = user;
    }

    public void show() {
        while (true) {
            System.out.println("║  📱 МЕНЮ КЛІЄНТА                      ║");
            System.out.println("👤 Користувач: " + currentUser.getFullName());
            System.out.println("\n📋 Доступні дії:");
            System.out.println("1. 🔍 Забронювати тур (пошук і вибір)");
            System.out.println("2. 📋 Переглянути мої бронювання");
            System.out.println("0. ⬅️  Вийти з акаунта");
            System.out.print("\n➤ Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    bookTourMenu();
                    break;
                case "2":
                    viewMyBookings();
                    break;
                case "0":
                    System.out.println("\n👋 До побачення, " + currentUser.getFullName() + "!");
                    return;
                default:
                    System.out.println("\n❌ Невірний вибір! Спробуйте ще раз.");
            }
        }
    }
    private void bookTourMenu() {
        while (true) {
            System.out.println("║  🔍 ПОШУК ТУРУ                        ║");
            System.out.println("\n📋 Оберіть спосіб пошуку:");
            System.out.println("1. 🌍 Знайти за країною/містом");
            System.out.println("2. 📝 Знайти за назвою туру");
            System.out.println("3. 💰 Знайти за ціновою категорією");
            System.out.println("4. 📅 Знайти за датою виїзду");
            System.out.println("5. 🎯 Знайти за типом путівки");
            System.out.println("6. 🏨 Знайти за типом проживання");
            System.out.println("7. 🚗 Знайти за транспортом");
            System.out.println("8. 📖 Переглянути всі доступні тури");
            System.out.println("0. ⬅️  Назад");
            System.out.print("\n➤ Ваш вибір: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    searchByCountryCity();
                    break;
                case "2":
                    searchByName();
                    break;
                case "3":
                    searchByPrice();
                    break;
                case "4":
                    searchByDate();
                    break;
                case "5":
                    searchByType();
                    break;
                case "6":
                    searchByLodging();
                    break;
                case "7":
                    searchByTransport();
                    break;
                case "8":
                    List<Tour> allTours = FileManager.loadTours();
                    displayToursAndBook(allTours);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\n❌ Невірний вибір!");
            }
        }
    }
    private void searchByCountryCity() {
        System.out.println("\n🌍 Пошук за місцем призначення");
        System.out.print("Введіть країну: ");
        String country = scanner.nextLine().trim();

        System.out.print("Введіть місто (або залиште порожнім для всієї країни): ");
        String city = scanner.nextLine().trim();

        List<Tour> tours = FileManager.loadTours();
        List<Tour> filtered = new ArrayList<>();

        for (Tour tour : tours) {
            boolean match = tour.getCountry().toLowerCase().contains(country.toLowerCase());
            if (match && !city.isEmpty()) {
                match = tour.getCity().toLowerCase().contains(city.toLowerCase());
            }
            if (match) {
                filtered.add(tour);
            }
        }

        displayToursAndBook(filtered);
    }
    private void searchByName() {
        System.out.println("\n📝 Пошук за назвою");
        System.out.print("Введіть назву туру або частину назви: ");
        String name = scanner.nextLine().trim();

        List<Tour> tours = FileManager.loadTours();
        List<Tour> filtered = new ArrayList<>();

        for (Tour tour : tours) {
            if (tour.getTitle().toLowerCase().contains(name.toLowerCase())) {
                filtered.add(tour);
            }
        }

        displayToursAndBook(filtered);
    }

    private void searchByPrice() {
        System.out.println("\n💰 Пошук за ціновою категорією");
        System.out.print("Мінімальна ціна (грн): ");
        double minPrice = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Максимальна ціна (грн): ");
        double maxPrice = Double.parseDouble(scanner.nextLine().trim());

        List<Tour> tours = FileManager.loadTours();
        List<Tour> filtered = new ArrayList<>();

        for (Tour tour : tours) {
            if (tour.getBasePrice() >= minPrice && tour.getBasePrice() <= maxPrice) {
                filtered.add(tour);
            }
        }

        displayToursAndBook(filtered);
    }
    private void searchByDate() {
        System.out.println("\n📅 Пошук за датою виїзду");
        System.out.print("Введіть дату виїзду (формат: YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();

        List<Tour> tours = FileManager.loadTours();
        List<Tour> filtered = new ArrayList<>();

        for (Tour tour : tours) {
            if (tour.getStartDate().equals(date)) {
                filtered.add(tour);
            }
        }

        displayToursAndBook(filtered);
    }
    private void searchByType() {
        System.out.println("\n🎯 Пошук за типом путівки");
        System.out.println("Доступні типи:");
        System.out.println("  • відпочинок");
        System.out.println("  • екскурсія");
        System.out.println("  • лікування");
        System.out.println("  • шопінг");
        System.out.println("  • круїз");
        System.out.print("\nВведіть тип: ");
        String type = scanner.nextLine().trim();

        List<Tour> tours = FileManager.loadTours();
        List<Tour> filtered = new ArrayList<>();

        for (Tour tour : tours) {
            if (tour.getType().equalsIgnoreCase(type)) {
                filtered.add(tour);
            }
        }

        displayToursAndBook(filtered);
    }
    private void searchByLodging() {
        System.out.println("\n🏨 Пошук за типом проживання");
        System.out.println("Доступні типи:");
        System.out.println("  • готель");
        System.out.println("  • мотель");
        System.out.println("  • пансіон");
        System.out.print("\nВведіть тип проживання: ");
        String lodging = scanner.nextLine().trim();

        System.out.print("Кількість зірок (1-5, або 0 для будь-якої): ");
        int stars = Integer.parseInt(scanner.nextLine().trim());

        List<Tour> tours = FileManager.loadTours();
        List<Tour> filtered = new ArrayList<>();

        for (Tour tour : tours) {
            boolean match = tour.getLodging().equalsIgnoreCase(lodging);
            if (match && stars > 0) {
                match = tour.getHotelStars() == stars;
            }
            if (match) {
                filtered.add(tour);
            }
        }

        displayToursAndBook(filtered);
    }
    private void searchByTransport() {
        System.out.println("\n🚗 Пошук за типом транспорту");
        System.out.println("Доступні типи:");
        System.out.println("  • літак");
        System.out.println("  • поїзд");
        System.out.println("  • автобус");
        System.out.println("  • лайнер");
        System.out.println("  • машина");
        System.out.print("\nВведіть тип транспорту: ");
        String transport = scanner.nextLine().trim();

        List<Tour> tours = FileManager.loadTours();
        List<Tour> filtered = new ArrayList<>();

        for (Tour tour : tours) {
            if (tour.getTransport().equalsIgnoreCase(transport)) {
                filtered.add(tour);
            }
        }

        displayToursAndBook(filtered);
    }
    private void displayToursAndBook(List<Tour> tours) {
        if (tours.isEmpty()) {
            System.out.println("\n❌ Тури не знайдено за вашим запитом!");
            System.out.println("💡 Спробуйте змінити критерії пошуку");
            return;
        }

        System.out.println("║  📋 ЗНАЙДЕНІ ТУРИ (" + tours.size() + ")                    ║");

        for (int i = 0; i < tours.size(); i++) {
            System.out.println("\n【 Тур #" + (i + 1) + " 】");
            System.out.println(tours.get(i));
        }

        System.out.print("\n➤ Введіть номер туру для бронювання (або 0 для повернення): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());

            if (choice > 0 && choice <= tours.size()) {
                createBooking(tours.get(choice - 1));
            } else if (choice != 0) {
                System.out.println("\n❌ Невірний номер туру!");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n❌ Введіть коректне число!");
        }
    }
    private void createBooking(Tour tour) {
        System.out.println("║  🎫 ОФОРМЛЕННЯ БРОНЮВАННЯ             ║");
        System.out.println("📋 Обраний тур: " + tour.getTitle());
        System.out.println("🌍 Напрямок: " + tour.getCountry() + ", " + tour.getCity());
        System.out.println("📅 Дата виїзду: " + tour.getStartDate());
        System.out.print("\n👨‍👩‍👧 Кількість дорослих: ");
        int adults = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("👶 Кількість дітей: ");
        int children = Integer.parseInt(scanner.nextLine().trim());

        double basePrice = tour.getBasePrice();
        double adultsPrice = adults * basePrice;
        double childrenPrice = children * (basePrice * 0.5);
        double totalPrice = adultsPrice + childrenPrice;

        System.out.println("║  💰 РОЗРАХУНОК ВАРТОСТІ               ║");
        System.out.println("📊 Базова ціна туру: " + String.format("%.2f", basePrice) + " грн");
        System.out.println("👨 Дорослих: " + adults + " × " + String.format("%.2f", basePrice) + " грн = " + String.format("%.2f", adultsPrice) + " грн");
        System.out.println("👶 Дітей: " + children + " × " + String.format("%.2f", basePrice * 0.5) + " грн = " + String.format("%.2f", childrenPrice) + " грн");
        System.out.println("─────────────────────────────────────────");
        System.out.println("🎯 ЗАГАЛЬНА ВАРТІСТЬ: " + String.format("%.2f", totalPrice) + " грн");

        System.out.print("\n❓ Підтвердити бронювання? (так/ні): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("так") || confirm.equals("yes") || confirm.equals("y")) {
            String bookingId = "B" + System.currentTimeMillis();
            String bookingDate = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            Booking booking = new Booking(
                    bookingId,
                    currentUser.getLogin(),
                    tour.getId(),
                    tour.getTitle(),
                    adults,
                    children,
                    totalPrice,
                    "Підтверджено",
                    bookingDate
            );

            FileManager.appendBooking(booking);

            System.out.println("║  ✅ БРОНЮВАННЯ УСПІШНЕ!               ║");
            System.out.println("🎫 ID вашого бронювання: " + bookingId);
            System.out.println("📅 Дата бронювання: " + bookingDate);
            System.out.println("💰 До сплати: " + String.format("%.2f", totalPrice) + " грн");
            System.out.println("\n✨ Гарного відпочинку!");
        } else {
            System.out.println("\n❌ Бронювання скасовано.");
        }
    }
    private void viewMyBookings() {
        System.out.println("║  📋 МОЇ БРОНЮВАННЯ                    ║");

        List<Booking> allBookings = FileManager.loadBookings();
        List<Booking> myBookings = new ArrayList<>();

        for (Booking booking : allBookings) {
            if (booking.getUserLogin().equals(currentUser.getLogin())) {
                myBookings.add(booking);
            }
        }

        if (myBookings.isEmpty()) {
            System.out.println("\n❌ У вас поки немає бронювань");
            System.out.println("💡 Виберіть тур і забронюйте його!");
        } else {
            System.out.println("\n🎯 Знайдено бронювань: " + myBookings.size());
            for (int i = 0; i < myBookings.size(); i++) {
                System.out.println("\n【 Бронювання #" + (i + 1) + " 】");
                System.out.println(myBookings.get(i));
            }

            double totalSum = 0;
            for (Booking booking : myBookings) {
                totalSum += booking.getFinalPrice();
            }
            System.out.println("💰 Загальна вартість всіх бронювань: " + String.format("%.2f", totalSum) + " грн");
        }
    }
}
