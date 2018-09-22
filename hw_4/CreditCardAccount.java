public class CreditCardAccount extends LiabilityAccount
{
    int accNo;
    String accType;
    String holderName;
    double amountOwed = 0;
    public CreditCardAccount(String accType, String holderName)
    {
        accNo = 1600000;
        this.accType = accType;
        this.holderName = holderName;

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
    public double getAmountOwed()
    {
        return amountOwed;
    }
    public boolean credit(double amount)
    {
        amountOwed += amount;
        return true;
    }
    public boolean debit(double amount)
    {
        amountOwed -= amount;
        return true;
    }
}