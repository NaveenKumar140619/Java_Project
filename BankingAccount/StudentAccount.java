package accountbanking;

public class StudentAccount extends Account {
    private static final double ZERO_BALANCE_LIMIT = 0.0;

    public StudentAccount(String accountNumber, String accountOwner, double balance, boolean isActive) {
        super(accountNumber, accountOwner, balance, isActive, "student");
    }

    @Override
    public void handleMonthlyCharges() {
        if (balance > ZERO_BALANCE_LIMIT) {
            balance = ZERO_BALANCE_LIMIT;
            logTransaction("Balance reduced to zero to meet student account policy.");
        }
    }
}
