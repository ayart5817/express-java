package MOC;

/**
 * есть разны типы певозок земля - куб вода-контенер воздух - багаж
 * общая система
 * абтрактный клас - типы перемещения и
 */
public abstract class TypeTransport {
    protected String name;
    protected String value;

    public TypeTransport(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public abstract void start();
}
