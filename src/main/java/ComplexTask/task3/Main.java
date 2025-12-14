package ComplexTask.task3;

public class Main {
    public static void main(String[] args) {
        BookBuilder builder = new BookBuilder();
        Book book = builder
                .setTitle("War and Peace")
                .setAuthor("Leo Tolstoy")
                .setDescription("A historical novel about the Napoleonic Wars. ")
                .build();


        BookProxy proxy = new BookProxy(book);

        String content = proxy.getContent();
        System.out.println("Content of the book: " + content);

        Book book2 = builder
                .setAuthor("Mark")
                .setDescription("Game develop")
                .setTitle("XXX")
                .build();

        BookProxy proxy2 = new BookProxy(book2);
        String content2 = proxy2.getContent();
        System.out.println("Content of the book: " + content2);

    }

}
