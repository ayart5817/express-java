package FreeTask.Class_Object;

public class Event {
    String title;
    String date;

    public Event(String title, String date) {
        this.title = title;
        this.date = date;
    }

    @Override
    public String toString() {
        return date + " — " + title ;
    }

    static void main(String[] args) {
        Event event = new Event("Встреча с клиентом", "12.05.2025");
        System.out.println(event.toString());
    }
}
