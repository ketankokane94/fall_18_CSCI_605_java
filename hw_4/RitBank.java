import java.util.Scanner;
import java.util.Vector;
public class RitBank //implements AccountMethods
{
    static Vector<BankAccount> bankAccount = new Vector<BankAccount>();
    public static void main(String args[])
    {
        String choice = "";
        do {
            displayMenu();
            Scanner choiceScanner = new Scanner(System.in);
            choice = choiceScanner.nextLine();
            switch(choice)
            {
                case "time":
                    break;
                case "open":
                    openAcc();
                    break;
                case "close":
                    closeAccount();
                    break;
                case "credit":
                    creditAccount();
                    break;
                case "debit":
                    debitAccount();
                    break;
                case "summary":
                    printSummary();
                    break;
            }
        }while(!choice.equals("exit"));
    }

    public static void displayMenu()
    {
        System.out.println("Enter on of the following commands : \n" +
                "\ttime - pass certain amount of time\n" +
                "\topen - open a new account\n" +
                "\tclose - close an account\n" +
                "\tcredit - credit an account\n" +
                "\tdebit - debit an account\n" +
                "\tsummary - display current bank accounts\n" +
                "\texit - exit program\n" +
                "What do you wanna do?");
    }

    public static void openAcc()
    {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What type of account\n" +
                "     0 - for savings\n" +
                "     1 - for checking\n" +
                "     2 - for credit card?\n");
        int type = inputScanner.nextInt();
        System.out.println("What is the customer's name ?\n");
        inputScanner = new Scanner(System.in);
        String holderName = inputScanner.nextLine();
        if(type == 0 || type == 1) {
            System.out.println("How much to deposit?\n");
            inputScanner = new Scanner(System.in);
            double startingAmount = inputScanner.nextDouble();
            if(type == 0) {
                bankAccount.add(new SavingsAccount("SAVINGS", holderName, startingAmount));
            }
            else
            {
                bankAccount.add(new CheckingAccount("CURRENT", holderName, startingAmount));
            }
        }
        else
        {
            bankAccount.add(new CreditCardAccount("CREDIT_CARD", holderName));
        }
    }

    public static int findAccount(int accNum)
    {
        int accIndex = 0;
        int indexFound = -1;
        while (accIndex < bankAccount.size())
        {
            if(bankAccount.get(accIndex).getAccNo() == accNum)
            {
                indexFound = accIndex;
                break;
            }
            accIndex++;
        }
        return indexFound;
    }

    public static void closeAccount()
    {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What is the account number?>");
        int accNum = inputScanner.nextInt();
        int indexFound = findAccount(accNum);
        if(indexFound == -1)
        {
            System.out.println("Account does not exist!");
        }
        else
        {
            boolean remove = bankAccount.remove(bankAccount.get(indexFound));
            if(!remove)
            {
                System.out.println("Account could not be removed!");
            }
        }
    }

    public static void creditAccount()
    {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What is the account number?>");
        int accNum = inputScanner.nextInt();
        inputScanner = new Scanner(System.in);
        System.out.println("How much?>");
        double amount = inputScanner.nextDouble();
        int indexFound = findAccount(accNum);
        if(indexFound == -1)
        {
            System.out.println("Account does not exist!!");
        }
        else
        {
            boolean credit = bankAccount.get(indexFound).credit(amount);
            if(!credit)
            {
                System.out.println("Cannot make this transaction. Insufficient funds!");
            }
        }
    }

    public static void debitAccount()
    {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("What is the account number?>");
        int accNum = inputScanner.nextInt();
        inputScanner = new Scanner(System.in);
        System.out.println("How much?>");
        double amount = inputScanner.nextDouble();
        int indexFound = findAccount(accNum);
        if(indexFound == -1)
        {
            System.out.println("Account does not exist!!");
        }
        else
        {
            boolean debit = bankAccount.get(indexFound).debit(amount);
        }
    }

    public static void printSummary()
    {
        System.out.println("BANK SUMMARY");
        int accIndex = 0;
        while(accIndex < bankAccount.size())
        {
            System.out.print("Account Number : " + bankAccount.get(accIndex).getAccNo() + "\n");
            System.out.print("\tAccount Type : " + bankAccount.get(accIndex).getAccType() + "\n");
            System.out.print("\t\tCustomer Name : " + bankAccount.get(accIndex).getHolderName() + "\n");
            if(bankAccount.get(accIndex) instanceof SavingsAccount) {
                SavingsAccount savingsAccount = (SavingsAccount) ((AssetAccount)bankAccount.get(accIndex));
                System.out.print("\t\tAccount Balance : $" + savingsAccount.getBalance() + "\n");
            }
            else if(bankAccount.get(accIndex) instanceof CheckingAccount) {
                CheckingAccount checkingAccount = (CheckingAccount) ((AssetAccount)bankAccount.get(accIndex));
                System.out.print("\t\tAccount Balance : $" + checkingAccount.getBalance() + "\n");
            }
            else
            {
                CreditCardAccount creditCardAccount = (CreditCardAccount) ((LiabilityAccount)bankAccount.get(accIndex));
                System.out.print("\t\tAmount Owed : $" + creditCardAccount.getAmountOwed() + "\n");
            }
            accIndex++;
        }
    }

}

