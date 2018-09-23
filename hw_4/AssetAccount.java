public abstract class AssetAccount extends BankAccount {

    Double accountBalance;

    public AssetAccount(AcountType accountType, String accountHolderName,Double accountBalance) {
        super(accountType, accountHolderName);
        this.accountBalance = accountBalance;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void debitIntoAccount(Double amount){
        if(amount > 0){
            accountBalance+=amount;
        }
    }

    public boolean creditFromAccount(Double amount){
        if(amount > 0 && amount <= accountBalance){
            accountBalance-=amount;
            return true;
        }
        return  false;
    }


}