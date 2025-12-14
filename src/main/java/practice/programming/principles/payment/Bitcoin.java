package practice.programming.principles.payment;

public class Bitcoin implements PaymentMethod {
    @Override
    public void payment(double amount) {
        System.out.println("Оплата через Bitcoin на сумму " + amount);
    }
}
