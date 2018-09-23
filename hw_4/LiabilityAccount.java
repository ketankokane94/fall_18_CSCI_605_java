public abstract class LiabilityAccount extends BankAccount
{

    double totalCreditLimit;
    double availableCreditLimit;

    public LiabilityAccount(AcountType accountType, String accoountHolderName) {
        super(accountType, accoountHolderName);
        totalCreditLimit = 900.0;
        availableCreditLimit = totalCreditLimit;
    }

    public void debitIntoAccount(Double payoffAmount){
        if(payoffAmount > 0 && getMoneyOwed() >= payoffAmount){
            availableCreditLimit+=payoffAmount;
        }
    }

    public boolean creditFromAccount(Double amount){
        if(availableCreditLimit > 0 && amount <= availableCreditLimit){
                availableCreditLimit-=amount;
                return  true;
        }

            return false;
    }

    public double getMoneyOwed(){
        return totalCreditLimit - availableCreditLimit;
    }


}