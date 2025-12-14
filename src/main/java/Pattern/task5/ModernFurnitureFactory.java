package Pattern.task5;

public class ModernFurnitureFactory implements FurnitureFactory{
    @Override
    public Chair createChar() {
        return new ModernChair();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }
}
