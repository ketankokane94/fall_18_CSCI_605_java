public class SavingsAccount extends AssetAccount
{
    int accNo;
    String accType;
    String holderName;
    double balance = 0;
    public SavingsAccount(String accType, String holderName, double startingAmount)
    {
        accNo = 1500000;
        this.accType = accType;
        this.holderName = holderName;
        balance += startingAmount;

    }
    public int getAccNo()
    {
        return accNo;
    }
    public String getAccType()
    {
        return accType;
    }
    public String getHolderName()
    {
        return holderName;
    }
    public double getBalance()
    {
        return balance;
    }
    public boolean credit(double amount)
    {
        if(balance < amount)
        {
            return false;
        }
        else
        {
            balance -= amount;
            return true;
        }
    }
    public boolean debit(double amount)
    {
        balance += amount;
        return true;
    }
}