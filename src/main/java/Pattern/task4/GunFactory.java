package Pattern.task4;

// GunFactory.java
public class GunFactory extends WeaponFactory {
    @Override public Weapon createWeapon() { return new Gun(); }
}
