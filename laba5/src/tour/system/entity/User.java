package tour.system.entity;

public class User {
    private String login;
    private String password;
    private String fullName;
    private String phone;
    private String email;

    public User(String login, String password, String fullName, String phone, String email) {
        this.login = login;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setPassword(String password) { this.password = password; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmail(String email) { this.email = email; }

    public String toFileString() {
        return login + "|" + password + "|" + fullName + "|" + phone + "|" + email;
    }

    public static User fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 5) {
            return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        return null;
    }
}