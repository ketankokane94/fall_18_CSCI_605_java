/* 
 * AssetAccount.java 
 * 
 * Version: 
 *     1
 * 
 * Revisions: 
 *     1
 */

public abstract class AssetAccount extends BankAccount {

    double accountBalance;
/**
 *  calls the BankAccount constructor to create a bank account and initialises the opening account bal
 * @param accountType
 * @param accountHolderName
 * @param accountBalance
 */
    public AssetAccount(AcountType accountType, String accountHolderName,double accountBalance) {
        super(accountType, accountHolderName);
        this.accountBalance = accountBalance;
    }
/**
 * returns the current balance of the account
 * @return
 */
    public Double getAccountBalance() {
        return accountBalance;
    }

    /**
     * adds the given amount to the account balance
     * @param amount
     */
    public void debitIntoAccount(double amount){
        if(amount > 0){
            accountBalance+=amount;
        }
    }
/**
 * takes out money from current account balance
 * @param amount
 * @return
 */
    public boolean creditFromAccount(double amount){
        if(amount > 0 && amount <= accountBalance){
            accountBalance-=amount;
            return true;
        }
        return  false;
    }


}