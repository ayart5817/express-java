package FreeTask.Class_Object;

public class Book {
    String title;
    String author;
    int year;


    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public String toString() {
        String r = ("Книга: " + title + " (" + year + "), автор – " + author);
        return r;
    }

    public static void main(String[] args) {
        Book book = new Book("Название книги", "Автор", 2000);
        System.out.println(book.toString());
    }
}
