/* 
 * LiabilityAccount.java 
 * 
 * Version: 
 *     1
 * 
 * Revisions: 
 *     1
 */

public abstract class LiabilityAccount extends BankAccount
{

    double totalCreditLimit;
    double availableCreditLimit;
/**
 *  calls the super constructor to craete the bank account 
 * and sets the total credit limit and initial available credit
 * @param accountType
 * @param accoountHolderName
 */
    public LiabilityAccount(AcountType accountType, String accoountHolderName) {
        super(accountType, accoountHolderName);
        totalCreditLimit = 900.0;
        availableCreditLimit = totalCreditLimit;
    }
/**
 * pays off the given amount from remaninbg debts
 * @param payoffAmount
 */
    public void debitIntoAccount(Double payoffAmount){
        if(payoffAmount > 0 && getMoneyOwed() >= payoffAmount){
            availableCreditLimit+=payoffAmount;
        }
    }

    /**
     * credits if the money can be credit from the account and also credits the amonnt
     * @param amount
     * @return
     */
    public boolean creditFromAccount(Double amount){
        if(availableCreditLimit > 0 && amount <= availableCreditLimit){
                availableCreditLimit-=amount;
                return  true;
        }

            return false;
    }
/**
 * returns the money owed by the account holder
 */
    public double getMoneyOwed(){
        return totalCreditLimit - availableCreditLimit;
    }


}