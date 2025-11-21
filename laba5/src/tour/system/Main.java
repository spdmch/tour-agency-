package tour.system;

import tour.system.menu.*;
import tour.system.util.FileManager;

public class Main {
    public static void main(String[] args) {
        FileManager.initializeFiles();

        MainMenu mainMenu = new MainMenu();
        mainMenu.show();
    }
}