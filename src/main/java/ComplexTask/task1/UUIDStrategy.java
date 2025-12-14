package ComplexTask.task1;

import java.util.UUID;

public class UUIDStrategy  implements ShorteningStrategy{
    @Override
    public String shorten(String originalUrl) {
        return UUID.randomUUID().toString().substring(0,8);
    }
}
