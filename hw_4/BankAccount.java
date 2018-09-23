import java.util.Random;

public abstract class BankAccount
{
    private Random random = new Random();
    int accountNo;
    AcountType accountType;
    String accountHolderName;


    public BankAccount(AcountType accountType, String accountHolderName) {
        this.accountNo = generateNewAccountNumber();
        this.accountType = accountType;
        this.accountHolderName = accountHolderName;
    }

    // todo: fix it to only 5 digits positive integers
    private int generateNewAccountNumber() {
        return  random.nextInt()%100;
    }


}

