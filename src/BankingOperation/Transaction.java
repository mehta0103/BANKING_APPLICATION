package BankingOperation;


import java.sql.*;
import java.util.Scanner;

public class Transaction {

    Connection con;
         Scanner sc= new Scanner(System.in);

        Connection EstablishConnection() throws SQLException {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankproject", "root", "Password@123");
        return con;
    }

            public void ViewTransactions() throws ClassNotFoundException, SQLException
            {
            Class.forName("com.mysql.cj.jdbc.Driver");
            EstablishConnection();
            System.out.println("Enter Account number to view All Transactions");
            long AccountnNo=new Scanner(System.in).nextLong();
            String Query= "select * from transactions where accountno="+AccountnNo;
            Statement st= con.createStatement();
            ResultSet rs= st.executeQuery(Query);
                System.out.format("%-20s%-20s%-20s%-20s\n","TransactionID","TransactionDate","TransactionType","Amount");
            while (rs.next())
            {
                System.out.format("%-20s%-20s%-20s%-20s\n",rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getFloat(4));
            }
        }

}
