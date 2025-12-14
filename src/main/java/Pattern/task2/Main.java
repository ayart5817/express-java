package Pattern.task2;

public class Main {
    static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.info(" START");
        Logger logger2 = Logger.getInstance();
        System.out.println(logger == logger2);
    }
}
