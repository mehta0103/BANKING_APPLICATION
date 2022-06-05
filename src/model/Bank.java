package model;

public class Bank {

    long accountNo;
    String accontType;
    String Name;
    String Address;
    long phoneNo;
    int age;
    double balance;

    public long getAccountNo() {
        return accountNo;
    }

    public String getAccontType() {
        return accontType;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public long getPhoneNo() {
        return phoneNo;
    }

    public int getAge() {
        return age;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "accountNo=" + accountNo +
                ", accontType='" + accontType + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", phoneNo=" + phoneNo +
                ", age=" + age +
                ", balance=" + balance +
                '}';
    }
}
