public abstract class BankAccount
{
    public abstract int getAccNo();
    public abstract String getAccType();
    public abstract String getHolderName();
    public abstract boolean credit(double amount);
    public abstract boolean debit(double amount);
}