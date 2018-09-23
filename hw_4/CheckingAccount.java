    /* 
 * CheckingAccount.java 
 * 
 * Version: 
 *     1
 * 
 * Revisions: 
 *     1
 */


public class CheckingAccount extends AssetAccount
{

/**
 * sets the limit of the transaction in checking account 
 */
    public void setPerTransactionLimit(Double perTransactionLimit) {
        this.perTransactionLimit = perTransactionLimit;
    }

    Double perTransactionLimit=0.0;
    public CheckingAccount(String accountHolderName,Double initialDeposit) {
        super(AcountType.CHECKING, accountHolderName,initialDeposit);
        setPerTransactionLimit(initialDeposit/2);
    }

    /**
     * @param amount
     * @return
     */
    @Override
    public boolean creditFromAccount(double amount){
    if(amount <= perTransactionLimit){
        return  super.creditFromAccount(amount);
    }
    return  false;
    }

}