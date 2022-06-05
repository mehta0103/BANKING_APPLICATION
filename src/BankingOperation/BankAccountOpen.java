package BankingOperation;
import java.sql.*;
import java.util.Scanner;
import java.util.Random;

public class BankAccountOpen {

    Connection con;
    Scanner sc= new Scanner(System.in);

    Connection EstablishConnection() throws SQLException {
         con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject","root","Password@123");
         return con;
    }
    public static int generateCustomerID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // first not 0 digit
        sb.append(random.nextInt(9) + 1);

        // rest of 11 digits
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }

        return Integer.valueOf(sb.toString()).intValue();
    }
    public static long generateAccountNo() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // first not 0 digit
        sb.append(random.nextInt(9) + 1);

        // rest of 11 digits
        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10));
        }

        return Long.valueOf(sb.toString()).longValue();
    }

     public void CreateAccount() throws ClassNotFoundException, SQLException {
        if(con!=null) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            EstablishConnection();
            System.out.println("Enter type of Account you Want to open......1 for SavingAccount and 2 for Pay Account ");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Enter your Name");
                String Name = sc.next();
                int Customerid = generateCustomerID();
                System.out.println("Enter your Address");
                String Address = sc.next();
                System.out.println("Enter your Phone Number");
                int number = sc.nextInt();
                PreparedStatement st = con.prepareStatement("insert into customer values(?,?,?,?)");
                st.setInt(1, Customerid);
                st.setString(2, Name);
                st.setString(3, Address);
                st.setInt(4, number);
                int rowAff = st.executeUpdate();
                //System.out.println("values inserted in customer");
                long AccountNO = generateAccountNo();
                PreparedStatement st1 = con.prepareStatement("insert into account values(?,?,?,?)");
                st1.setLong(1, AccountNO);
                st1.setString(2, "Saving");
                st1.setString(3, Name);
                st1.setInt(4, Customerid);
                int rowAffc = st1.executeUpdate();
                //System.out.println("values inserted in account");
                System.out.println("Enter a amount to open Account");
                float balance = sc.nextFloat();
                System.out.println("Enter Ammount for loan......?");
                 int loan = sc.nextInt();
                 PreparedStatement st2 = con.prepareStatement("insert into savingAccount values(?,?,?,?)");
                    st2.setLong(1, AccountNO);
                    st2.setString(2, Name);
                    st2.setFloat(3,balance);
                    st2.setFloat(4,loan);
                    int row = st2.executeUpdate();
                    //System.out.println("values inserted in saving Account");

                    if (rowAffc > 0 && rowAff > 0 && row > 0) {
                        System.out.println("Account created Succesfully");
                        System.out.println("Your account Number is : "+AccountNO);
                        System.out.println("AccountHolder : "+Name);
                        System.out.println("customer id : "+ Customerid);
                        System.out.println("Opening Balance : "+ balance);
                        System.out.println("Address : "+ Address);
                    } else {
                        System.out.println("Problem in Account opening");
                    }

            } else if (choice == 2) {
                System.out.println("Enter your Name");
                String Name = sc.next();
                int Customerid1 = generateCustomerID();
                System.out.println("Enter your Address");
                String Address = sc.next();
                System.out.println("Enter your Phone Number");
                int number = sc.nextInt();
                PreparedStatement st = con.prepareStatement("insert into customer values(?,?,?,?)");
                st.setInt(1, Customerid1);
                st.setString(2, Name);
                st.setString(3, Address);
                st.setInt(4, number);
                int rowAff = st.executeUpdate();
               // System.out.println(" insered in customer");
                long AccountNO1 = generateAccountNo();
                PreparedStatement st1 = con.prepareStatement("insert into account values(?,?,?,?)");
                st1.setLong(1, AccountNO1);
                st1.setString(2, "Pay");
                st1.setString(3, Name);
                st1.setInt(4, Customerid1);
                int rowAffc = st1.executeUpdate();
                //System.out.println("value inserted in account");
                System.out.println("Enter a amount to open Account");
                float balance = sc.nextFloat();
                PreparedStatement st2 = con.prepareStatement("insert into payAccount values(?,?,?)");
                st2.setLong(1, AccountNO1);
                st2.setString(2, Name);
                st2.setFloat(3, balance);
                int row = st2.executeUpdate();
                if (rowAffc > 0 && rowAff > 0 && row > 0) {
                    System.out.println("Account created Succesfully");
                    System.out.println("Your Account NUmber is "+AccountNO1);
                    System.out.println("AccountHolder : "+Name);
                    System.out.println("customer id : "+ Customerid1);
                    System.out.println("Opening Balance : "+ balance);
                    System.out.println("Address : "+ Address);
                } else {
                    System.out.println("Problem in Account opening");
                }


            } else {
                System.out.println("Enter a valid choice");
            }
        }
     }

               public void checkBalance() throws ClassNotFoundException, SQLException {
                   Class.forName("com.mysql.cj.jdbc.Driver");
                   EstablishConnection();
                   System.out.println("which type of account ypu have....1 for saving and 2 for payAccount");
                   int choice= sc.nextInt();
                   if(choice==1)
                   {
                       System.out.println("Enter your  Account Number");
                       long AccountNo = sc.nextLong();
                       Statement stmt = con.createStatement();
                       String Query = "Select balance from SavingAccount where accountno=" + AccountNo;
                       ResultSet rs = stmt.executeQuery(Query);
                       float balance = 0.0f;
                       while (rs.next()) {
                           balance = rs.getFloat("balance");
                       }
                       System.out.println("Your Account balance is " + balance);
                   }
                   else if(choice==2)
                   {
                       System.out.println("Enter your  Account Number");
                       long AccountNo = sc.nextLong();
                       Statement stmt = con.createStatement();
                       String Query = "Select balance from payAccount where accountno=" + AccountNo;
                       ResultSet rs = stmt.executeQuery(Query);
                       float balance = 0.0f;
                       while (rs.next()) {
                           balance = rs.getFloat("balance");
                       }
                       System.out.println("Your Account balance is " + balance);
                   }
                   else
                   {
                       System.out.println("Enter a valid choice");
                   }
               }
}
