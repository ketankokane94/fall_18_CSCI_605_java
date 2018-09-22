public abstract class AssetAccount extends BankAccount
{
    //public abstract boolean openAcc();
    //public abstract boolean closeAcc();
    public abstract int getAccNo();
    public abstract String getAccType();
    public abstract String getHolderName();
    public abstract double getBalance();
    public abstract boolean credit(double amount);
    public abstract boolean debit(double amount);
}