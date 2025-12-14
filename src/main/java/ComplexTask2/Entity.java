package ComplexTask2;

public abstract class Entity {
    private String name;
    private int age;
    private boolean isActive;

    public Entity(int age, boolean isActive, String name) {
        this.age = age;
        this.isActive = isActive;
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public boolean isActive() {
        return isActive;
    }




}
