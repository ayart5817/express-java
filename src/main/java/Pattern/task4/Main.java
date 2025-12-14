package Pattern.task4;

public class Main {
    static void main(String[] args) {
        WeaponFactory[] factories = {new SwordFactory(), new BowFactory(), new GunFactory()
        };

        for (WeaponFactory factory : factories) {
            Weapon w = factory.getWeapon();
            w.attack();
        }
        System.out.println();
    }
}
