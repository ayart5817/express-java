package FreeTask.OOP.Magazine;

/**
 * библиотека содержит несколько
 * один документ, разного типа
 * Книга -  Название и автор
 * Журнал - Hазвание и номер выпуска
 * Газета - название и дату выпуска
 *
 *
 *
 */
public abstract class PublishingMagazine {
    protected String name;
    protected String info;

    public PublishingMagazine(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }
    abstract void getInfoObject();
}
