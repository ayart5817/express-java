package Pattern.task3;

public class Car implements Transport{
    @Override
    public void start() {
        System.out.println("start auto");
    }

    @Override
    public void stop() {
        System.out.println("stop auto");
    }
}
