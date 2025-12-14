package FreeTask.OOP.TranspotCompany;


/**
 * По транспортная компания - возможность разных транспортов и запускать транспортировку
 * - Крабль (вмесстимость колю контейнеров) - Грузовик (Количество коробок) - Самолет(количество багажа)
 *  транспортировка () кажого кажого транспорта Транспорт начал транспортировку value Type
 *
 *  Абстрактиный клас - (уникальный идентификатор)
 *  Управление (management)
 *
 */

public abstract class TransportEntities {
    protected String ID;
    protected int capacity;
    protected String type;

    public String getID() {
        return ID;
    }

    public int  getCapacity() {
        return capacity;
    }

    public TransportEntities(String ID, int  capacity, String type) {
        this.ID = ID;
        this.capacity = capacity;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract void start();

}
