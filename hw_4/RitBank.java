import java.util.Scanner;
import java.util.Vector;
/* 
 * RitBank.java 
 *  
 * Version: 
 *     1
 * 
 * Revisions: 
 *     1
 */
public class RitBank {

    private static Vector<BankAccount> bankAccounts = new Vector<BankAccount>();
    private String mainMenuMessage = new String();
    Scanner scanner;

    /**
     *  main method of the class
     * @param args
     */
    public static void main(String args[]) {
        RitBank ritBank = new RitBank();
        ritBank.scanner = new Scanner(System.in);
        String choice = new String();
        do {
            ritBank.displayMenu();
            choice = ritBank.scanner.next();
            switch (choice) {
                case "time":
                    ritBank.time();
                    break;
                case "open":
                    ritBank.openAccount();
                    break;
                case "close":
                    ritBank.closeAccount();
                   break;
                case "credit":
                     ritBank.creditFromAccount();
                    break;
                case "debit":
                    ritBank.debitIntoAccount();
                    break;
                case "summary":
                    ritBank.printAccountSummary();
                    break;
            }
        } while (!choice.equals("exit"));
        ritBank.scanner.close();
    }
/**
 * calculates the interest for each account based on its type and user entered month
 */
    private void time() {
        System.out.println("How months should pass?>");
        int months = scanner.nextInt();
        for(BankAccount bankAccount: bankAccounts){
        if(bankAccount instanceof SavingsAccount){
            System.out.println(bankAccount.accountHolderName+" earned $"+((SavingsAccount) bankAccount).interestAccured(months)+" in interest in "+months+" months. Account Balance is now $"+((SavingsAccount) bankAccount).getAccountBalance());
        }
        else if(bankAccount instanceof CreditCardAccount){
            ((CreditCardAccount) bankAccount).printAccountStatementPerMonth(months);
        }
        }
    }
/**
 * displays a menu to the user
 */
    private void displayMenu() {
        if (mainMenuMessage.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder("Enter on of the following commands : \n");
            stringBuilder.append("\ttime - pass certain amount of time\n");
            stringBuilder.append("\topen - open a new account\n");
            stringBuilder.append("\tclose - close an account\n");
            stringBuilder.append("\tcredit - credit an account\n");
            stringBuilder.append("\tdebit - debit an account\n");
            stringBuilder.append("\tsummary - display current bank accounts\n");
            stringBuilder.append("\texit - exit program\n");
            stringBuilder.append("What do you wanna do?");
            mainMenuMessage = stringBuilder.toString();

        }
        System.out.println(mainMenuMessage);


    }
/**
 * handles the opening of a account
 * can openn Savings, checking and credit card account
 */
    private void openAccount() {
        displayAccountTypeMenu();
        int accountType = scanner.nextInt();
        System.out.println("What is the customer's name ?\n");
        String accountHolderName = scanner.next();
        if (accountType == 0 || accountType == 1) {
            System.out.println("How much to deposit?\n");
            double initialDeposit = scanner.nextDouble();
            if (accountType == 0) {
                bankAccounts.add(new SavingsAccount(accountHolderName, initialDeposit));
            } else {
                bankAccounts.add(new CheckingAccount(accountHolderName, initialDeposit));
            }
        } else {
            bankAccounts.add(new CreditCardAccount(accountHolderName));
        }
        System.out.println();
    }

    /**
     * asks for user which account is to be opened
     */
    private void displayAccountTypeMenu() {
        System.out.println("What type of account\n" +
                "     0 - for savings\n" +
                "     1 - for checking\n" +
                "     2 - for credit card?\n");
    }

/**
 * gets the account with the given account number from the vectors of all the accounts of the bank
 */
    private BankAccount getAccount(int accountNumber) {
            for (BankAccount bankAccount:bankAccounts){
                if(bankAccount.accountNo==accountNumber)
                    return bankAccount;
            }
            return  null;
        }
/**
 * handles the case of closing the account
 */
        private  void closeAccount()
           {
               BankAccount bankAccount = getBankAccount();
               if(bankAccount==null)
                   System.out.println("No account found with this number");
               else {
                   if(bankAccount instanceof AssetAccount ){
                       System.out.println("Account closed please collect cash: "+ ((AssetAccount) bankAccount).getAccountBalance());
                       bankAccounts.remove(bankAccount);
                   }
                   else if (bankAccount instanceof LiabilityAccount && ((LiabilityAccount) bankAccount).getMoneyOwed()> 0){
                        System.out.println("To close this account please pay off your outstanding balance of :"+((LiabilityAccount) bankAccount).getMoneyOwed());
                   }
                   else {
                       bankAccounts.remove(bankAccount);
                   }
               }

           }
/**
 * allows and handles crediting from different accounts of the bank
 */
           private void creditFromAccount()
           {
               BankAccount bankAccount = getBankAccount();
               if(bankAccount!=null ){
                   System.out.println("How much?>");
                   double amount = scanner.nextDouble();
                   if( bankAccount instanceof AssetAccount){
                       ((AssetAccount) bankAccount).creditFromAccount(amount);
                       System.out.println("amount credited from your account no "+ bankAccount.accountNo + "  new balance  "+((AssetAccount) bankAccount).getAccountBalance());
                   }
                   else if (bankAccount!=null && bankAccount instanceof LiabilityAccount){
                       if(((LiabilityAccount) bankAccount).creditFromAccount(amount)){
                           System.out.println("amount credited off on your credit card available credit limit is =  "+((LiabilityAccount) bankAccount).availableCreditLimit);
                       }
                       else {
                           System.out.println("Insufficient available credit limit");
                       }
                   }
               }
               else {
                   System.out.println("Account does not exist!!");
               }
           }

           /**
            * asks users for the account number to do processing on
            * @return
            */
    private BankAccount getBankAccount() {
        System.out.println("What is the account number?>");
        int accountNumber = scanner.nextInt();
        return getAccount(accountNumber);
    }

    /**
     * handles and allows debits into all the types of account in the bank
     */
    private void debitIntoAccount() {
            BankAccount bankAccount = getBankAccount();
            if(bankAccount!=null ){
                System.out.println("How much?>");
                double amount = scanner.nextDouble();
                if( bankAccount instanceof AssetAccount){
                    ((AssetAccount) bankAccount).debitIntoAccount(amount);
                    System.out.println("amount debited to your account no "+ bankAccount.accountNo + "  new balance  "+((AssetAccount) bankAccount).getAccountBalance());
                }
                else if (bankAccount!=null && bankAccount instanceof LiabilityAccount){
                    ((LiabilityAccount) bankAccount).debitIntoAccount(amount);
                    System.out.println("amount payed off on your credit card available credit limit is =  "+((LiabilityAccount) bankAccount).availableCreditLimit);
                }
            }
            else {
                System.out.println("Account does not exist!!");
            }
        }

        /**
         * gives a detailed report of all the accounts in the bank
         */
        private void printAccountSummary() {

        StringBuilder stringBuilder = new StringBuilder("BANK SUMMARY\n");

        for (BankAccount bankAccount : bankAccounts) {

            stringBuilder.append("Account Number : ").append(bankAccount.accountNo).append("\n");
            stringBuilder.append("\t Account Type : ").append(bankAccount.accountType).append("\n");
            stringBuilder.append("\t\tCustomer Name : ").append(bankAccount.accountHolderName).append("\n");

            if (bankAccount instanceof AssetAccount) {
                Double accountBalance = ((AssetAccount) bankAccount).getAccountBalance();
                stringBuilder.append("\t\tAccount Balance : $").append(String.format("%.2f", accountBalance)).append("\n");
            }
            else if(bankAccount instanceof LiabilityAccount){
                double moneyOwed =  ((LiabilityAccount) bankAccount).getMoneyOwed();
                stringBuilder.append("\t\tAmount Owed : $" + String.format("%.2f",moneyOwed) + "\n");
            }
            System.out.println(stringBuilder.toString());
            stringBuilder.setLength(0);
        }

    }
}




