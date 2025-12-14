package FreeTask.OOP.Transport;


/**
 * Грузовые - грузовик коробки , корабли контейнеры, Самолеты - груз багаж
 * система - набор разных поставок
 * ф-ц все эти поставки произвести "вывод сообщения о перевозке типа груза" 10 контейнеров 50 багажей 100 Коробок
 *
 *
 */
public abstract class Transportation {
    private int valueCargo;
    private String cargo;

    public Transportation(int valueCargo, String cargo) {
        this.valueCargo = valueCargo;
        this.cargo = cargo;
    }

    public int getValueCargo() {
        return valueCargo;
    }

    public String getCargo() {
        return cargo;
    }
    abstract public void startTransportation();
}
