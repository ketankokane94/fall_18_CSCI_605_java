/* 
 * SavingAccount.java 
 * 
 * Version: 
 *     1
 * 
 * Revisions: 
 *     1
 */
public class SavingsAccount extends AssetAccount
{
    private  double RATE_OF_INTEREST = 0.03;
    int monthlyAllowedTransactions ;
    public SavingsAccount(String accountHolderName,Double initialDeposit) {
        super(AcountType.SAVINGS, accountHolderName,initialDeposit);
        setMonthlyAllowedTransactions(8);
    }

    /**
     * set the limit of transactions per month for saving account
     */
    public void setMonthlyAllowedTransactions(int monthlyAllowedTransactions) {
        this.monthlyAllowedTransactions = monthlyAllowedTransactions;
    }

    /**
     * calculates the interest accured on the account
     */
    public Double interestAccured(int months){
        double interest = (accountBalance * months * RATE_OF_INTEREST)/100;
        accountBalance += interest;
        return interest;
    }
}
