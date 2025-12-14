package ComplexTask.task3;

public class Book {
    private final String title;
    private final String content;
    private final String author;
    private final String description;

    public String getTitle() { return title; }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Book(String title, String content, String author, String description) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.description = description;
    }

    @Override
    public String toString() {
        return "title=" + title + '\'' +
                " content= " + content + '\'' +
                " author= " + author + '\'' +
                " description= " + description + '\''
                ;
    }
}
