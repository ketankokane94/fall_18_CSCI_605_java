
public class CreditCardAccount extends LiabilityAccount
{
    private double RATE_OF_INTEREST = 13.4;

    public CreditCardAccount(String accountHolderName) {
        super(AcountType.CREDIT_CARD, accountHolderName);
    }

    public void printAccountStatementPerMonth(int months){
        StringBuilder sb = new StringBuilder();
        for (int index = 1; index <= months ; index++) {
        sb.append(accountHolderName).append(" charged $").append(getInterest());
        sb.append(" in interest after ").append(index).append(" months. Account Balance is now $").append(getMoneyOwed()).append("\n");
        sb.append("Credit Card bill sent to customer ").append(accountHolderName);
        sb.append(" for account number ").append(accountNo).append(" in the amount of $").append(getMoneyOwed());
        System.out.println(sb.toString());
        sb.setLength(0);
        }

    }

    private double getInterest() {
        double interest = (getMoneyOwed() * 1 * RATE_OF_INTEREST)/100;
        availableCreditLimit -= interest;
        return interest;
    }
}
