import java.util.Random;

/* 
 * BankAccount.java 
 * 
 * Version: 
 *     1
 * 
 * Revisions: 
 *     1
 */


public abstract class BankAccount
{
    private Random random = new Random();
    int accountNo;
    AcountType accountType;
    String accountHolderName;

/**
 * constructor which creates a new account in the bank
 * @param accountType SAVINGS CHECKING CREDIT CARD
 * @param accountHolderName name of account hodler
 */
    public BankAccount(AcountType accountType, String accountHolderName) {
        this.accountNo = generateNewAccountNumber();
        this.accountType = accountType;
        this.accountHolderName = accountHolderName;
    }

    // todo: fix it to only 5 digits positive integers
    /**
     * returns a randomly generated account number
     * @return
     */
    private int generateNewAccountNumber() {
       return new Object().hashCode();
    }


}

