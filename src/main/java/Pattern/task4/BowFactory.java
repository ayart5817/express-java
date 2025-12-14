package Pattern.task4;

// BowFactory.java
public class BowFactory extends WeaponFactory {
    @Override public Weapon createWeapon() { return new Bow(); }
}
