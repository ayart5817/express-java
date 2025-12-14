package Pattern.task3;

public class Bicycle implements Transport{
    @Override
    public void start() {
        System.out.println("Start bicycle");
    }

    @Override
    public void stop() {
        System.out.println("Stop bicycle");
    }
}
