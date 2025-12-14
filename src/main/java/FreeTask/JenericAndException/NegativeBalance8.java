package FreeTask.JenericAndException;

public class NegativeBalance8<T extends Number> {
    T balance;

    public NegativeBalance8(T balance) {

        this.balance = balance;
    }

    public void checkBalance(T amount) {
        if (amount == null) {
            System.out.println("Сумма не может быть null");
        }

        if (amount.doubleValue() < 0) {
            throw new NegativeBalanceException(balance + " Не может быть отрицательным");
        }
        balance = amount;
        System.out.println("Ok");

    }

    static void main(String[] args) {
        NegativeBalance8<Integer> n1 = new NegativeBalance8<>(100);
        // n1.checkBalance(null);
        n1.checkBalance(-101);
    }


}
