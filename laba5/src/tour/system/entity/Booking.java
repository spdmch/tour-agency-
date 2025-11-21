package tour.system.entity;

public class Booking {
    private String id;
    private String userLogin;
    private String tourId;
    private String tourTitle;
    private int adults;
    private int children;
    private double finalPrice;
    private String status;
    private String bookingDate;

    public Booking(String id, String userLogin, String tourId, String tourTitle,
                   int adults, int children, double finalPrice, String status, String bookingDate) {
        this.id = id;
        this.userLogin = userLogin;
        this.tourId = tourId;
        this.tourTitle = tourTitle;
        this.adults = adults;
        this.children = children;
        this.finalPrice = finalPrice;
        this.status = status;
        this.bookingDate = bookingDate;
    }

    public String getId() { return id; }
    public String getUserLogin() { return userLogin; }
    public String getTourId() { return tourId; }
    public String getTourTitle() { return tourTitle; }
    public int getAdults() { return adults; }
    public int getChildren() { return children; }
    public double getFinalPrice() { return finalPrice; }
    public String getStatus() { return status; }
    public String getBookingDate() { return bookingDate; }

    public String toFileString() {
        return id + "|" + userLogin + "|" + tourId + "|" + tourTitle + "|" +
                adults + "|" + children + "|" + finalPrice + "|" + status + "|" + bookingDate;
    }

    public static Booking fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 9) {
            return new Booking(
                    parts[0], parts[1], parts[2], parts[3],
                    Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                    Double.parseDouble(parts[6]), parts[7], parts[8]
            );
        }
        return null;
    }

    @Override
    public String toString() {
        return "ID бронювання: " + id + "\n" +
                "Тур: " + tourTitle + "\n" +
                "Дорослих: " + adults + ", Дітей: " + children + "\n" +
                "Кінцева ціна: " + String.format("%.2f", finalPrice) + " грн\n" +
                "Статус: " + status + "\n" +
                "Дата бронювання: " + bookingDate + "\n";
    }
}

