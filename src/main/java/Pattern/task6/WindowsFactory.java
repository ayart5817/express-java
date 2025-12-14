package Pattern.task6;

public class WindowsFactory implements GUIFactory{
    @Override
    public Button createButton() {
        System.out.println("Создана кнопка для Windows");
        return new WindowsButton();
    }

    @Override
    public Window createWindow() {
        System.out.println("Создана окно для Windows");
        return new WindowsWindow();
    }

    @Override
    public Menu createMenu() {
        System.out.println("Создан элемент Меню для Windows");
    return new WindowsMenu();
    }
}
