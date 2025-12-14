package practice.programming.principles.payment;

public class CreditCard implements PaymentMethod {

    @Override
    public void payment(double amount) {
        System.out.println("Оплата кредитной картой на сумму " + amount);
    }
}
