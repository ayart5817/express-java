package ComplexTask.task3;

public class BookProxy {

    private Book book;

    public BookProxy(Book book) {
        this.book = book;
    }

    public String getContent() {
    if (book.getContent() == null) {
        System.out.println("Книга загружается ... " + book.getTitle());
        String loadedContent = "Полный текст книги " + book.getTitle();
        book = new BookBuilder()
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor())
                .setDescription(book.getDescription())
                .setContent(loadedContent)
                .build();
    }
    return book.getContent();
}


}
