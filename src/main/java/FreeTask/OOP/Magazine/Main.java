package FreeTask.OOP.Magazine;

public class Main {
    static void main() {


       PublishingMagazine book1 = new Book("bookOne","Автор 1");
       PublishingMagazine magazine1 = new Magazine("Журнал 1", "номер выпуска 0001");
       PublishingMagazine newsPaper1 = new NewsPaper("Газета 1", "дату выпуска: 22.10.2025 ");


       Biblical biblical = new Biblical();
       biblical.add(book1);
       biblical.printInfo(book1);
       biblical.add(magazine1);
       biblical.printInfo(magazine1);
        biblical.add(newsPaper1);
       biblical.printInfo(newsPaper1);

    }
}