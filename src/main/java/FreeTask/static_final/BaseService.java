package FreeTask.static_final;

public class BaseService {
    protected final void shutdown() {
        System.out.println("Наследование разрешено, но переопределение нет!");
    }
}
