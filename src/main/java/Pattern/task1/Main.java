package Pattern.task1;

public class Main {
    static void main(String[] args) {


        ConfigurationManager cfg1 = ConfigurationManager.getInstance();
        System.out.println(cfg1.toString());
        ConfigurationManager cfg2 = ConfigurationManager.getInstance();
        System.out.println(cfg2.toString());
        System.out.println(cfg1 == cfg2);

    }
}
