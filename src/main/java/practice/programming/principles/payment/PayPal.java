package practice.programming.principles.payment;

public class PayPal implements PaymentMethod {
    @Override
    public void payment(double amount) {
        System.out.println("Оплата через PayPal на сумму " + amount);
    }
}
