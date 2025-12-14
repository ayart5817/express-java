package Pattern.task6;

import java.util.Objects;
import java.util.Scanner;

public class Application {
    Button button;
    Window window;
    Menu menu;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.window = factory.createWindow();
        this.menu = factory.createMenu();
    }

    public void used() {
        window.draw();
        button.click();
        menu.open();
    }

    static GUIFactory getFactory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("W – Windows M – MacOS");
        String platform = scanner.next();
        if (Objects.equals(platform, "W")) {
            return new WindowsFactory();
        } else if
        (Objects.equals(platform, "M")) {
            return new MacFactory();
        } else {
            System.out.println("неизвестная платформа, по умолчанию WIN");

            return new WindowsFactory();
        }
    }

    static void main(String[] args) {
        GUIFactory factory = getFactory();


        Application app = new Application(factory);
        app.used();
    }
}