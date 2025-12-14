package practice.programming.principles;

interface Bird {
}


interface Flyable {
    void fly();
}

class Sparrow implements Bird, Flyable {
    @Override
    public void fly() {
        System.out.println("Воробей летит");
    }
}


class Penguin implements Bird {
    // public void fly() {
    //   System.out.println("Пингвин летит");
    // Нет метода
}
