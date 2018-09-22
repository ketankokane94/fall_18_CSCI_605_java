public abstract class LiabilityAccount extends BankAccount
{
    //public abstract boolean openAcc();
    //public abstract boolean closeAcc();

    public abstract int getAccNo();
    public abstract String getAccType();
    public abstract String getHolderName();
    public abstract double getAmountOwed();
    public abstract boolean credit(double amount);
    public abstract boolean debit(double amount);
}