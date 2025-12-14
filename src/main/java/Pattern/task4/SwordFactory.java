package Pattern.task4;

// SwordFactory.java
public class SwordFactory extends WeaponFactory {
    @Override public Weapon createWeapon() { return new Sword(); }
}

