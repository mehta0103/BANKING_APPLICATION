package BankingOperation;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;


public class BankWithdraw {
    Connection con;
    Scanner sc= new Scanner(System.in);

    Connection EstablishConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "Password@123");
        return con;
    }
    public static int generateTransactionID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // first not 0 digit
        sb.append(random.nextInt(9) + 1);

        // rest of 11 digits
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10));
        }

        return Integer.valueOf(sb.toString()).intValue();
    }

    public void withdrawFromSaving() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        EstablishConnection();
        System.out.println("Enter your  Account Number");
        long AccountNo= sc.nextLong();
        System.out.println("Enter ammount for withdrawl");
        float withdraw= sc.nextFloat();
        Statement stmt= con.createStatement();
        String Query= "Select balance from SavingAccount where accountno="+AccountNo;
        ResultSet rs=stmt.executeQuery(Query);
        float balance=0.0f;
        while(rs.next())
        {
             balance= rs.getFloat("balance");
        }
       if (withdraw<=balance)
        {
            PreparedStatement s1 = con.prepareStatement("Update savingAccount set balance=balance-? where accountNo=? ");
            s1.setFloat(1, withdraw);
            s1.setLong(2, AccountNo);
            int rowAffected = s1.executeUpdate();
            int id = generateTransactionID();
            PreparedStatement s2 = con.prepareStatement("insert into transactions values(?,curdate(),?,?,?)");
            s2.setInt(1, id);
            s2.setString(2, "Withdraw");
            s2.setFloat(3, withdraw);
            s2.setLong(4, AccountNo);
            int row2 = s2.executeUpdate();
            if (rowAffected > 0 && row2 > 0) {
                System.out.println("Transaction succesfully done");

            } else {
                System.out.println("transaction failed.........try after some time");
            }
        }
        else
        {
            System.out.println("insuffcient balance");
        }
    }
    public void withdrawFromPayAccount() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        EstablishConnection();
        System.out.println("Enter your  Account Number");
        long AccountNo= sc.nextLong();
        System.out.println("Enter ammount for withdrawl");
        float withdraw= sc.nextFloat();
        Statement stmt= con.createStatement();
        String Query= "Select balance from payAccount where accountno="+AccountNo;
        ResultSet rs= stmt.executeQuery(Query);
        float balance=0.0f;
        while(rs.next())
        {
            balance = rs.getFloat("balance");
        }
        if(withdraw<=balance)
        {
            PreparedStatement s1= con.prepareStatement("Update payAccount set balance=balance-? where accountNo=? ");
            s1.setFloat(1,withdraw);
            s1.setLong(2,AccountNo);
            int rowAffected= s1.executeUpdate();
            int id= generateTransactionID();
            PreparedStatement s2= con.prepareStatement("insert into transactions values(?,curdate(),?,?,?)");
            s2.setInt(1,id);
            s2.setString(2,"Withdraw");
            s2.setFloat(3,withdraw);
            s2.setLong(4,AccountNo);
            int row2=s2.executeUpdate();
            if(rowAffected>0 && row2>0)
            {
                System.out.println("Transaction succesfully done");

            }
            else
            {
                System.out.println("transaction falid.........try after some time");
            }
        }
        else
        {
            System.out.println("you dont save suficient balance in your account");
        }

    }
}
