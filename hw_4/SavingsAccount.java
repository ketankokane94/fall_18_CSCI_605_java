
public class SavingsAccount extends AssetAccount
{
    private  double RATE_OF_INTEREST = 0.03;
    int monthlyAllowedTransactions ;
    public SavingsAccount(String accountHolderName,Double initialDeposit) {
        super(AcountType.SAVINGS, accountHolderName,initialDeposit);
        setMonthlyAllowedTransactions(8);
    }

    public void setMonthlyAllowedTransactions(int monthlyAllowedTransactions) {
        this.monthlyAllowedTransactions = monthlyAllowedTransactions;
    }

    public Double interestAccured(int months){
        double interest = (accountBalance * months * RATE_OF_INTEREST)/100;
        accountBalance += interest;
        return interest;
    }
}
