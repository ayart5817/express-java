package ComplexTask.task3;

public class BookBuilder {
    private String title;
    private String content;
    private String author;
    private String description;


    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setDescription(String description) {
        this.description = description;
        return this;
    }


    public Book build() {
        return new Book(title, content, author, description);
    }
}
