package Pattern.task8;

public class Game {
    static void main(String[] args) {
        Character warrior = new Character.Builder()
                .armor(10)
                .damage(10)
                .magic(0)
                .name("Warrior")
                .build();


        Character mage = new Character.Builder()
                .armor(2)
                .damage(1)
                .magic(9)
                .health(60)
                .name("Mage")
                .build();
        Character def = new Character.Builder().build();


        System.out.println(warrior.toString());
        System.out.println(mage.toString());
        System.out.println(def.toString());
    }
}
