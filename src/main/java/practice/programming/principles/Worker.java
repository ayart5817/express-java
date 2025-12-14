package practice.programming.principles;

interface Worker {
    void work();

}
interface Eatable {
    void eat();
}
class Programmer implements Worker {
    @Override
    public void work() {
        System.out.println("Программист пишет код");
    }
}
class HomeWorker implements Worker, Eatable {
    @Override
    public void work() {
        System.out.println("Удаленщик пишет код дома");
    }

    @Override
    public void eat() {
        System.out.println("Удаленщик ест дома");
    }
}