package tour.system.menu;

import tour.system.entity.Tour;
import tour.system.util.FileManager;
import java.util.*;

public class AdminMenu {
    private Scanner scanner = new Scanner(System.in);

    public void show() {
        while (true) {
            System.out.println("║  🔐 ПАНЕЛЬ АДМІНІСТРАТОРА             ║");
            System.out.println("\n📋 Управління турами:");
            System.out.println("1. 📖 Переглянути всі тури");
            System.out.println("2. 🔍 Знайти тур за назвою");
            System.out.println("3. ➕ Додати новий тур");
            System.out.println("4. ✏️  Змінити існуючий тур");
            System.out.println("5. 🗑️  Видалити тур");
            System.out.println("0. ⬅️  Вихід з панелі адміністратора");
            System.out.print("\n➤ Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    viewAllTours();
                    break;
                case "2":
                    findTourByName();
                    break;
                case "3":
                    addTour();
                    break;
                case "4":
                    editTour();
                    break;
                case "5":
                    deleteTour();
                    break;
                case "0":
                    System.out.println("\n👋 Вихід з панелі адміністратора");
                    return;
                default:
                    System.out.println("\n❌ Невірний вибір! Спробуйте ще раз.");
            }
        }
    }

    // 1. ПЕРЕГЛЯД ВСІХ ТУРІВ
    private void viewAllTours() {
        System.out.println("║  📖 ВСІ ТУРИ В СИСТЕМІ                ║");

        List<Tour> tours = FileManager.loadTours();

        if (tours.isEmpty()) {
            System.out.println("\n❌ База турів порожня!");
        } else {
            System.out.println("\n🎯 Всього турів у базі: " + tours.size());
            for (int i = 0; i < tours.size(); i++) {
                System.out.println("\n【 Тур #" + (i + 1) + " 】");
                System.out.println(tours.get(i));
            }
        }
    }
    private void findTourByName() {
        System.out.println("║  🔍 ПОШУК ТУРУ                        ║");
        System.out.print("Введіть назву туру або частину назви: ");
        String name = scanner.nextLine().trim();

        List<Tour> tours = FileManager.loadTours();
        boolean found = false;

        for (Tour tour : tours) {
            if (tour.getTitle().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(tour);
                found = true;
            }
        }

        if (!found) {
            System.out.println("\n❌ Тур з такою назвою не знайдено!");
        }
    }

    private void addTour() {
        System.out.println("║  ➕ ДОДАВАННЯ НОВОГО ТУРУ             ║");

        String id = "T" + (System.currentTimeMillis() % 100000);
        System.out.println("🆔 ID туру (згенеровано автоматично): " + id);

        System.out.print("\n📋 Назва туру: ");
        String title = scanner.nextLine().trim();

        System.out.print("🎯 Тип (відпочинок/екскурсія/лікування/шопінг/круїз): ");
        String type = scanner.nextLine().trim();

        System.out.print("🌍 Країна: ");
        String country = scanner.nextLine().trim();

        System.out.print("🏙️  Місто: ");
        String city = scanner.nextLine().trim();

        System.out.print("📅 Дата виїзду (формат YYYY-MM-DD): ");
        String startDate = scanner.nextLine().trim();

        System.out.print("⏰ Кількість ночей: ");
        int nights = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("🚗 Транспорт (літак/поїзд/автобус/лайнер/машина): ");
        String transport = scanner.nextLine().trim();

        System.out.print("🏨 Проживання (готель/мотель/пансіон): ");
        String lodging = scanner.nextLine().trim();

        System.out.print("⭐ Кількість зірок (1-5): ");
        int stars = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("🍽️  Харчування (RO/BB/HB/FB/AI): ");
        String meal = scanner.nextLine().trim();

        System.out.print("💰 Базова ціна (грн): ");
        double price = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("🎭 Екскурсії (перелічіть через кому): ");
        String excursions = scanner.nextLine().trim();

        Tour newTour = new Tour(id, title, type, country, city, startDate, nights,
                transport, lodging, stars, meal, price, excursions);

        FileManager.appendTour(newTour);


        System.out.println("║  ✅ ТУР УСПІШНО ДОДАНО!               ║");
        System.out.println("🆔 ID туру: " + id);
        System.out.println("📋 Назва: " + title);
    }

    private void editTour() {
        System.out.println("║  ✏️  РЕДАГУВАННЯ ТУРУ                 ║");
        System.out.print("Введіть назву туру для редагування: ");
        String name = scanner.nextLine().trim();

        List<Tour> tours = FileManager.loadTours();
        Tour tourToEdit = null;
        int tourIndex = -1;

        for (int i = 0; i < tours.size(); i++) {
            if (tours.get(i).getTitle().equalsIgnoreCase(name)) {
                tourToEdit = tours.get(i);
                tourIndex = i;
                break;
            }
        }

        if (tourToEdit == null) {
            System.out.println("\n❌ Тур не знайдено!");
            return;
        }

        System.out.println("\n📋 Поточна інформація про тур:");
        System.out.println(tourToEdit);

        System.out.println("\n💡 Підказка: якщо поле не потрібно змінювати - натисніть Enter");

        System.out.print("\n💰 Нова ціна [поточна: " + tourToEdit.getBasePrice() + " грн]: ");
        String priceStr = scanner.nextLine().trim();
        if (!priceStr.isEmpty()) {
            tourToEdit.setBasePrice(Double.parseDouble(priceStr));
            System.out.println("✅ Ціна оновлена");
        }

        System.out.print("📅 Нова дата виїзду [поточна: " + tourToEdit.getStartDate() + "]: ");
        String dateStr = scanner.nextLine().trim();
        if (!dateStr.isEmpty()) {
            tourToEdit.setStartDate(dateStr);
            System.out.println("✅ Дата оновлена");
        }

        System.out.print("⏰ Нова кількість ночей [поточна: " + tourToEdit.getNights() + "]: ");
        String nightsStr = scanner.nextLine().trim();
        if (!nightsStr.isEmpty()) {
            tourToEdit.setNights(Integer.parseInt(nightsStr));
            System.out.println("✅ Тривалість оновлена");
        }

        // Редагування типу харчування
        System.out.print("🍽️  Нове харчування [поточне: " + tourToEdit.getMeal() + "]: ");
        String mealStr = scanner.nextLine().trim();
        if (!mealStr.isEmpty()) {
            tourToEdit.setMeal(mealStr);
            System.out.println("✅ Харчування оновлено");
        }

        // Редагування екскурсій
        System.out.print("🎭 Нові екскурсії [поточні: " + tourToEdit.getExcursions() + "]: ");
        String excStr = scanner.nextLine().trim();
        if (!excStr.isEmpty()) {
            tourToEdit.setExcursions(excStr);
            System.out.println("✅ Екскурсії оновлено");
        }

        // Оновлюємо тур у списку і зберігаємо
        tours.set(tourIndex, tourToEdit);
        FileManager.saveTours(tours);

        System.out.println("║  ✅ ТУР УСПІШНО ОНОВЛЕНО!             ║");
    }

    // 5. ВИДАЛЕННЯ ТУРУ
    private void deleteTour() {
        System.out.println("║  🗑️  ВИДАЛЕННЯ ТУРУ                   ║");
        System.out.print("Введіть назву туру для видалення: ");
        String name = scanner.nextLine().trim();

        List<Tour> tours = FileManager.loadTours();
        Tour tourToDelete = null;

        for (Tour tour : tours) {
            if (tour.getTitle().equalsIgnoreCase(name)) {
                tourToDelete = tour;
                break;
            }
        }

        if (tourToDelete == null) {
            System.out.println("\n❌ Тур не знайдено!");
            return;
        }

        // Показуємо тур, який буде видалено
        System.out.println("\n⚠️  Тур для видалення:");
        System.out.println(tourToDelete);

        System.out.print("\n❓ ВИ ВПЕВНЕНІ? Підтвердити видалення? (так/ні): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("так") || confirm.equals("yes") || confirm.equals("y")) {
            // Видаляємо тур зі списку
            tours.remove(tourToDelete);
            // Зберігаємо оновлений список
            FileManager.saveTours(tours);

            System.out.println("║  ✅ ТУР УСПІШНО ВИДАЛЕНО!             ║");
        } else {
            System.out.println("\n❌ Видалення скасовано.");
        }
    }
}
