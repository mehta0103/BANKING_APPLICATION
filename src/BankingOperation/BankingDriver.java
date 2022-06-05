package BankingOperation;

import java.sql.SQLException;
import java.util.Scanner;

public class BankingDriver {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        BankAccountOpen b1= new BankAccountOpen();
        BankWithdraw b2= new BankWithdraw();
        BankDeposit b3= new BankDeposit();
        Transaction t1= new Transaction();
        b1.EstablishConnection();
        System.out.println("===============WELCOME TO IDBC BANKING SERVICES===============");
        System.out.println("Do you have a Account.......????");
        char choice=new Scanner(System.in).next().charAt(0);
        if(choice=='y'|| choice=='Y')
        {
            System.out.println("Select 1 for SavingAccount and 2 for PayAccount");
            int choice1=new Scanner(System.in).nextInt();
            if(choice1==1)
            {
                System.out.println("===============Select Banking Service You Want===============");
                System.out.println("Select 1 for Deposit, 2 for Withdrawl, 3 view all transaction records, 4 to check balance");
                int choice2=new Scanner(System.in).nextInt();
                if(choice2==1)
                {
                     b3.depositIntoSaving();
                }
                else if(choice2==2)
                {
                      b2.withdrawFromSaving();
                }
                else if(choice2==3)
                {
                   t1.ViewTransactions();
                }
                else if(choice2==4)
                {
                   b1.checkBalance();
                }
                else
                {
                    System.out.println(" Enter a valid Choice");
                }

            }
            else if(choice1==2)
            {
                System.out.println("===============Select Banking Service You Want===============");
                System.out.println("Select 1 for Deposit, 2 for Withdrawl, 3 to view all transaction records,4 for checking balance");
                int choice3=new Scanner(System.in).nextInt();
                if(choice3==1)
                {
                    b3.depositintoPAyAccount();
                }
                else if(choice3==2)
                {
                    b2.withdrawFromPayAccount();
                }
                else if(choice3==3)
                {
                  t1.ViewTransactions();
                }
                else if(choice3==4)
                {
                    b1.checkBalance();
                }
                else
                {
                    System.out.println("Enter A valid Choice");
                }

            }

        }
        else if(choice=='N'|| choice=='n')
        {
            System.out.println("Please create An Account");
            System.out.println("Please Enter Details Asked");
            b1.CreateAccount();

        }
        else
        {
            System.out.println(" please Enter A valid Choice");
        }

    }
}
