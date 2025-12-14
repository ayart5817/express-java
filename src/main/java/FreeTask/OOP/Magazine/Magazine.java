package FreeTask.OOP.Magazine;

class Magazine  extends PublishingMagazine{

    public Magazine(String name, String info) {
        super(name, info);
    }

    @Override
    void getInfoObject() {
        System.out.println( getName() + " " + getInfo());

    }


}
class Book  extends PublishingMagazine{

    public Book  (String name, String info) {
        super(name, info);
    }

    @Override
    void getInfoObject() {
        System.out.println( getName() + " " + getInfo());

    }


}
class NewsPaper  extends PublishingMagazine{

    public NewsPaper (String name, String info) {
        super(name, info);
    }

    @Override
    void getInfoObject() {
        System.out.println( getName() + " " + getInfo());

    }


}
