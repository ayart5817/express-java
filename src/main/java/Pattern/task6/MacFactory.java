package Pattern.task6;

public class MacFactory implements GUIFactory{
    @Override
    public Button createButton() {
        System.out.println("Создана кнопка для MacOS");
        return new MacButton();
    }

    @Override
    public Window createWindow() {
        System.out.println("Создана окно для MacOS");
        return new MacWindows();
    }

    @Override
    public Menu createMenu() {
        System.out.println("Создан элемент Меню для MacOS");
        return new MacMenu();
    }
}
