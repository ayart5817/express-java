package FreeTask.Class_Object;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.Objects;

public class Message implements Comparable<Message> {
    String from;
    String to;
    Instant timestamp;
    String content;

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(from, message.from) && Objects.equals(to, message.to) && Objects.equals(timestamp, message.timestamp) && Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, timestamp, content);
    }

    public Message(String from, String to, Instant timestamp, String content) {
        this.from = from;
        this.to = to;
        this.timestamp = timestamp;
        this.content = content;
    }


    @Override
    public int compareTo(@NotNull Message o) {
        return this.timestamp.compareTo(o.timestamp);

    }


}
