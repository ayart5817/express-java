package Pattern.task4;

public class Bow implements Weapon {
    @Override
    public void attack() {
        System.out.println("Выстрел из лука");
    }

    @Override
    public String getName() {
        return "Лук";
    }
}
