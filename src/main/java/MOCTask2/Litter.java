package MOCTask2;

 class Litter extends  Parcel{

    public Litter(int distance) {
        super(distance);
    }

    @Override
    public void calculateFinalCost() {
        int n = getDistance() * getPrice();
        System.out.println("Письмо отправлено на расстояние " +getDistance() + " общая стоимость: " +n);
    }

}
class Shipment extends  Parcel {
    int extraCost = 100;

    public Shipment(int distance) {
        super(distance);
    }

    @Override
    public void calculateFinalCost() {
        int n = getDistance() * getPrice() + extraCost;
        System.out.println("Посылка отправлено на расстояние " + getDistance() + " общая стоимость: " + n + " Надбавка к стоимости " + extraCost);
    }
}
class Fragle extends  Parcel {
    int extraCost = 50;

    public Fragle(int distance) {
        super(distance);
    }

    @Override
    public void calculateFinalCost() {
        int n = (getDistance() * getPrice()) + extraCost;
        System.out.println("Хрупкий отправлено на расстояние " + getDistance() + " общая стоимость: " + n + " Надбавка к стоимости " + extraCost);
    }
}