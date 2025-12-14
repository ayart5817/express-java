package Pattern.task5;

public class ClassicFurnitureFactory implements FurnitureFactory{
    @Override
    public Chair createChar() {
        return new ClassicChar();
    }

    @Override
    public Table createTable() {
        return new ClassicTable();
    }
}
