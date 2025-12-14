package FreeTask.Class_Object;

import java.util.Objects;

public class LibraryBook {
    String isbn;
    String title;
    String author;

   @Override
   public boolean equals(Object o) {
       if (o == null || o.getClass() != getClass()) return false;
       if (this == o) return true;
       LibraryBook book = (LibraryBook) o;
       return Objects.equals(isbn,book.isbn);
   }

   @Override
   public int hashCode() {
       return Objects.hash(isbn);
   }

    @Override
    public String toString() {
        return "LibraryBook " +
                "isbn = " + isbn +
                ", title = " + title +
                ", author = " + author
                ;
    }

    public LibraryBook(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    static void main(String[] args) {
        LibraryBook book1 = new LibraryBook("1234-5678", "Java code", "Kylikov");
        LibraryBook book2 = new LibraryBook("1234-5677", "Java code p2", "Kylikov");
        LibraryBook book3 = new LibraryBook("1234-5677", "Java code p3", "Kylikov");
        System.out.println(book3.equals(book2) + " – True");
        System.out.println(book3.equals(book1) + " – False");
        System.out.println(book2.equals(book1) + " – False");
   }

}
