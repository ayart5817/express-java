package FreeTask.JenericAndException;

class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException(String balance) {
        super(". Текущий баланс: " + balance);
    }
}