import java.util.LinkedList;
import java.util.Scanner;


public class Demo{
    public static void main(String[] args) {
        Scanner user_input=new Scanner(System.in);
        Customer new_cus;
        Bank cusList = new Bank();
        while(true){
            System.out.println("----------***********----------");
            System.out.println("Welcome to Bank");
            System.out.println("Register new customer press 1");
            System.out.println("Search registerd customer press 2");
            System.out.println("Create account to registered customer press 3");
            System.out.println("Search account press 4");
            System.out.println("Delete customer press 5");
            System.out.println("Delete account press 6");
            System.out.println("----------***********----------\n\n");
            int input=user_input.nextInt();
            if(input==1){
                System.out.print("Enter customer name: ");
                user_input.nextLine();
                String cus_name=user_input.nextLine();
                System.out.print("Enter NIC: ");
                String Nic=user_input.nextLine();
                System.out.print("Enter customer address: ");
                String address=user_input.nextLine();

                new_cus = new Customer(cus_name,Nic,address);
                cusList.addCus(new_cus);
                
            }else if(input==2){
                try {
                    System.out.print("Enter customer name: ");
                    user_input.nextLine();
                    String cus_name=user_input.nextLine();
                    System.out.print("Enter NIC: ");
                    String Nic=user_input.nextLine();
                    new_cus=cusList.searchCustomer(cus_name, Nic);
                    System.out.println("\nCustomer name: "+new_cus.getName());
                    System.out.println("Customer NIC: "+new_cus.getNIC());     
                    System.out.println("Customer address: "+new_cus.getAddress()+"\n");
                } catch (Exception e) {
                        System.out.println("\n\nCustomer not found!!\n\n");
                }
            }else if(input==3){
                System.out.print("Enter customer name: ");
                user_input.nextLine();
                String cus_name=user_input.nextLine();
                System.out.print("Enter NIC: ");
                String Nic=user_input.nextLine();
                cusList.addAccount(cus_name, Nic);
            }else if(input==4){
                System.out.print("Enter customer name: ");
                user_input.nextLine();
                String cus_name=user_input.nextLine();
                System.out.print("Enter NIC: ");
                String Nic=user_input.nextLine();
                cusList.searchAccount(cus_name, Nic);
            }else if(input==5){
                System.out.print("Enter customer name: ");
                user_input.nextLine();
                String cus_name=user_input.nextLine();
                System.out.print("Enter NIC: ");
                String Nic=user_input.nextLine();
                cusList.deleteCustomer(cus_name, Nic);
            }else if(input==6){
                System.out.print("Enter customer name: ");
                user_input.nextLine();
                String cus_name=user_input.nextLine();
                System.out.print("Enter NIC: ");
                String Nic=user_input.nextLine();
                cusList.deleteAccount(cus_name, Nic);
            }else{
                System.out.println("\n\nInvalid input..!!!\n\n");
            }
            //user_input.close();
        }
    }
}

class Bank {
    Scanner user_input=new Scanner(System.in);
    LinkedList<Customer> customerList = new LinkedList<Customer>();

    public void addCus(Customer cus){
        customerList.add(cus);
        System.out.println("\n\nCustomer registerd successfully..!!!\n\n");
    }

    Customer searchCustomer(String cus_name, String Nic){
        for(Customer cus: customerList){
            if(cus.getName().equals(cus_name) && cus.getNIC().equals(Nic)){
                return cus;
            }
        }
        return null;
    }

    Customer addAccount(String cus_name, String Nic){
        int i=0;
        for(Customer cus: customerList){
            if(cus.getName().equals(cus_name) && cus.getNIC().equals(Nic)){
                Account new_acc;

                System.out.print("Enter account number: ");
                Integer accNo=user_input.nextInt();
                System.out.print("Enter account balance: ");
                Double accBal=user_input.nextDouble();
                System.out.print("Enter account type: ");
                user_input.nextLine();
                String accType=user_input.nextLine();

                new_acc=new Account(accNo, accBal, accType);
                cus.addAcc(new_acc);
                break;
            }
            i++;
        }
        if(i==customerList.size()){
            System.out.println("\nCustomer not found..!!\n");
        }
        return null;
    }

    Customer deleteAccount(String cus_name, String Nic){
        int i=0;
        for(Customer cus: customerList){
            if(cus.getName().equals(cus_name) && cus.getNIC().equals(Nic)){
                System.out.print("Enter account number: ");
                Integer accNo=user_input.nextInt();
                cus.delAcc(accNo);
                break;
            }
            i++;
        }
        if(i==customerList.size()){
            System.out.println("\nCustomer not found..!!\n");
        }
        return null;
    }

    Customer deleteCustomer(String cus_name, String Nic){
        int i=0;
        for(Customer cus: customerList){
            if(cus.getName().equals(cus_name) && cus.getNIC().equals(Nic)){
                customerList.remove(customerList.indexOf(cus));
                System.out.println("\n\nAccount deleted successfully..!!!\n\n");
                i++;
                break;
            }else{
                i++;
            }
        }
        if(i==customerList.size()){
            System.out.println("\nCustomer not found..!!\n");
        }
        return null;
    }

    Customer searchAccount(String cus_name, String Nic){
        int i=0;
        for(Customer cus: customerList){
            if(cus.getName().equals(cus_name) && cus.getNIC().equals(Nic)){
                cus.print_acc();
                break;
            }
            i++;
        }
        if(i==customerList.size()){
            System.out.println("\nCustomer not found..!!\n");
        }
        return null;
    }
}

class Customer{
    private String name;
    private String NIC;
    private String address;

    LinkedList<Account> accountList=new LinkedList<Account>();

    void print_acc(){
        if(accountList.size()==0){
            System.out.println("\nCustomer not yet created account..!!\n");
        }else{
            for(Account acc: accountList){
                System.out.println("\nAccount number: "+acc.getAccNo());
                System.out.println("Account balance: "+acc.getAccBal());
                System.out.println("Account type: "+acc.getAccType()+"\n");
            }
        } 
    }

    public void addAcc(Account acc){
        accountList.add(acc);
        System.out.println("\n\nAccount created successfully..!!!\n\n");
    }

    public void delAcc(Integer acc_no){
        int i=0;
        for(Account acc: accountList){
            if(acc.getAccNo().equals(acc_no)){
                accountList.remove(accountList.indexOf(acc));
                System.out.println("\n\nAccount deleted successfully..!!!\n\n");
                i++;
                break;
            }else{
                i++;
            }
        }
        if(i==accountList.size()){
            System.out.println("\nAccount not found..!!\n");
        }
    }

    public Customer(String name, String NIC, String address) {
        this.name = name;
        this.NIC = NIC;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public String getNIC() {
        return NIC;
    }
    public String getAddress() {
        return address;
    }
}

class Account{
    private Integer AccNo;
    private double AccBal;
    private String AccType;

    public Account(Integer AccNo, double AccBal, String AccType) {
        this.AccNo = AccNo;
        this.AccBal = AccBal;
        this.AccType = AccType;
    }

    public Integer getAccNo() {
        return AccNo;
    }
    public double getAccBal() {
        return AccBal;
    }
    public String getAccType() {
        return AccType;
    }
}