package FreeTask.static_final;

import java.time.Instant;
import java.time.LocalDateTime;

public class Event {
    private final Instant createTime;


    public Event() {
        this.createTime = Instant.now();
    }

    public Instant getCreateTime() {
        return createTime ;
    }

    public static void main(String [] arg) {
        Event event = new Event();
        System.out.println(event.getCreateTime());
        System.out.println(LocalDateTime.now());

    }
}
