package Pattern.task4;

public class Sword implements Weapon {
    @Override
    public void attack() {
        System.out.println("Удар мечем");
    }

    @Override
    public String getName() {
        return "Меч";
    }
}
