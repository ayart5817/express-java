package Pattern.task4;

public class Gun implements Weapon{
    @Override
    public void attack() {
        System.out.println("Выстрел из ружья");
    }

    @Override
    public String getName() {
        return "Ружьё";
    }
}
