package Pattern.task5;

public class FurnitureClient {
    private Chair chair;
    private Table table;

    public FurnitureClient(FurnitureFactory factory) {
        this.chair = factory.createChar();
        this.table = factory.createTable();
    }
    public void enjoyFurniture() {
        chair.sitOn();
        table.use();
    }

    static void main(String[] args) {
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        FurnitureClient modernRoom = new FurnitureClient(modernFactory);
        modernRoom.enjoyFurniture();
        FurnitureFactory classicFactory = new ClassicFurnitureFactory();
        FurnitureClient classicRoom = new FurnitureClient(classicFactory);
        classicRoom.enjoyFurniture();
    }
}
