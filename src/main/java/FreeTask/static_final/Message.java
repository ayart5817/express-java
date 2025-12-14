package FreeTask.static_final;

public class Message {
    private final String content;

    private Message(String message) {
        this.content = message.trim();
    }

    // 🏭 Фабричный метод: создаёт НОВЫЙ объект при каждом вызове
   public static Message create(String content) {
        return new Message(content);
   }

   public String toString() {
        return this.content;
   }
}
