package Pattern.task8;

public final class Character {
    private final int health;
    private final int damage;
    private final int armor;
    private final int magic;
    private final String name;

    private Character(String name, int damage, int armor, int magic, int health) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.armor = armor;
        this.magic = magic;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public int getMagic() {
        return magic;
    }

    public int getArmor() {
        return armor;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return " === " + name + " === \n" + "health = " + health + " damage = " + damage + " armor = " + armor + " magic = " + magic;
    }

    public static class Builder {
        private String name = "default";
        private int health = 100;
        private int damage = 10;
        private int armor = 0;
        private int magic = 0;

        public Builder name(String name) {
            this.name = name;
            return this;
        }


        public Builder damage(int damage) {
            this.damage = damage;
            return this;
        }

        public Builder health(int health) {
            this.health = health;
            return this;
        }

        public Builder armor(int armor) {
            this.armor = armor;
            return this;
        }

        public Builder magic(int magic) {
            this.magic = magic;
            return this;
        }

        public Character build() {
            return new Character(name, damage, armor, magic, health);
        }
    }

}
