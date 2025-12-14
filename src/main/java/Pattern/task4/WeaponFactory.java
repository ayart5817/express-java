package Pattern.task4;

public abstract class WeaponFactory {
    public abstract Weapon createWeapon();

    public Weapon getWeapon() {
        Weapon weapon = createWeapon();
        System.out.println("Оружие создано " + weapon.getName());
    return weapon;
    }
}
