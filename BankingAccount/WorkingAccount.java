package accountbanking;

public class WorkingAccount extends Account {
    private static final double MINIMUM_BALANCE = 5000.0;
    private static final double MONTHLY_CHARGE = 560.0;

    public WorkingAccount(String accountNumber, String accountOwner, double balance, boolean isActive) {
        super(accountNumber, accountOwner, balance, isActive, "working");
    }

    @Override
    public void handleMonthlyCharges() {
        if (balance < MINIMUM_BALANCE) {
            double shortfall = MINIMUM_BALANCE - balance;
            balance -= MONTHLY_CHARGE;
            logTransaction("Shortfall of $" + shortfall + " charged with fee: $" + MONTHLY_CHARGE);
            
            if (balance < 0) {
                balance = 0;
                isActive = false;
                logTransaction("Account deactivated due to insufficient balance.");
            }
        }
    }
}
