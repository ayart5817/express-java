package FreeTask.OOP;

public class BancAccount {
    protected double balance;

    public void deposit(double amount) {
        balance = balance + amount;
        System.out.println("Внесли " + amount);
        System.out.println("остаток " + balance);
    }

    public void withdraw(double amount) {
        if (balance>amount) {
            balance = balance - amount;
            System.out.println("Сняли " + amount);
            System.out.println("остаток " + balance);
        }
    }

    static void main() {
        BancAccount bancAccount = new BancAccount();
        bancAccount.deposit(1000.00);
        bancAccount.withdraw(500.00);
    }
}
