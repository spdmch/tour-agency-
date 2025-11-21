package tour.system.entity;

public class Tour {
    private String id;
    private String title;
    private String type;
    private String country;
    private String city;
    private String startDate;
    private int nights;
    private String transport;
    private String lodging;
    private int hotelStars;
    private String meal;
    private double basePrice;
    private String excursions;

    public Tour(String id, String title, String type, String country, String city,
                String startDate, int nights, String transport, String lodging,
                int hotelStars, String meal, double basePrice, String excursions) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.country = country;
        this.city = city;
        this.startDate = startDate;
        this.nights = nights;
        this.transport = transport;
        this.lodging = lodging;
        this.hotelStars = hotelStars;
        this.meal = meal;
        this.basePrice = basePrice;
        this.excursions = excursions;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getType() { return type; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getStartDate() { return startDate; }
    public int getNights() { return nights; }
    public String getTransport() { return transport; }
    public String getLodging() { return lodging; }
    public int getHotelStars() { return hotelStars; }
    public String getMeal() { return meal; }
    public double getBasePrice() { return basePrice; }
    public String getExcursions() { return excursions; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setType(String type) { this.type = type; }
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public void setNights(int nights) { this.nights = nights; }
    public void setTransport(String transport) { this.transport = transport; }
    public void setLodging(String lodging) { this.lodging = lodging; }
    public void setHotelStars(int hotelStars) { this.hotelStars = hotelStars; }
    public void setMeal(String meal) { this.meal = meal; }
    public void setBasePrice(double basePrice) { this.basePrice = basePrice; }
    public void setExcursions(String excursions) { this.excursions = excursions; }

    public String toFileString() {
        return id + "|" + title + "|" + type + "|" + country + "|" + city + "|" +
                startDate + "|" + nights + "|" + transport + "|" + lodging + "|" +
                hotelStars + "|" + meal + "|" + basePrice + "|" + excursions;
    }

    public static Tour fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 13) {
            return new Tour(
                    parts[0], parts[1], parts[2], parts[3], parts[4],
                    parts[5], Integer.parseInt(parts[6]), parts[7], parts[8],
                    Integer.parseInt(parts[9]), parts[10], Double.parseDouble(parts[11]), parts[12]
            );
        }
        return null;
    }

    @Override
    public String toString() {
        return
                "ID: " + id + "\n" +
                "Назва: " + title + "\n" +
                "Тип: " + type + "\n" +
                "Країна: " + country + ", Місто: " + city + "\n" +
                "Дата виїзду: " + startDate + "\n" +
                "Тривалість: " + nights + " ночей\n" +
                "Транспорт: " + transport + "\n" +
                "Проживання: " + lodging + " (" + hotelStars + " зірок)\n" +
                "Харчування: " + meal + "\n" +
                "Базова ціна: " + String.format("%.2f", basePrice) + " грн\n" +
                "Екскурсії: " + excursions + "\n";
    }
}
