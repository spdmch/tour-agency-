package tour.system.entity;

public class Admin {
    private String login;
    private String password;

    public Admin(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }

    public String toFileString() {
        return login + "|" + password;
    }

    public static Admin fromFileString(String line) {
        String[] parts = line.split("\\|");
        if (parts.length == 2) {
            return new Admin(parts[0], parts[1]);
        }
        return null;
    }
}