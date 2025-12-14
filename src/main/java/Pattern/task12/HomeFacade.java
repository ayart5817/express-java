package Pattern.task12;

public class HomeFacade {
    private final Light light;
    private final AirConditioned ac;
    private final SecuritySystem security;

    public HomeFacade() {
        this.light = new Light();
        this.ac = new AirConditioned();
        this.security = new SecuritySystem();
    }

    public void leaveHome() {
        System.out.println("————Режим дома никого нет————");
        light.Off();
        ac.conditionedOff();
        security.arm();
        System.out.println();
    }

    public void comeHome() {
        System.out.println("————Вернулся в дом————");
        light.On();
        ac.conditionedOn();
        security.disarm();
        System.out.println();
    }

    public void nightMod() {
        System.out.println("————Ночной режим————");
        light.Off();
        ac.conditionedOff();
        security.disarm();
        System.out.println();
    }


}
