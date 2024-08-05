
import java.util.Scanner;



class atm{
    float Balance;
    int pin=1234;
    public void checkpin(){
        System.out.println("Enter your pin:");
        Scanner sc=new Scanner(System.in);
        int enteredpin=sc.nextInt();
        if(enteredpin==pin){
            menu();
        }
        else{
            System.out.println("Enter a valid pin:");
            menu();
        }
    }
    public void menu(){
        System.out.println("Enter your choice:");
        System.out.println("1. Check A/C Balance:");
        System.out.println("2. Withdraw money:");
        System.out.println("3. Deposite money");
        System.out.println("4. Exit");
        Scanner sc=new Scanner(System.in);
        int opt=sc.nextInt();

        if(opt == 1){
            checkBalance();
        }
        else if(opt == 2){
            withdrawMoney();
        }
        else if(opt == 3){
            depositeMoney();
        }
        else if(opt == 4){
            System.out.println("Collect your ATM Card\n Thanks you for using ATM MACHINE")
            return;
        }
        else{
            System.out.println("Enter a valid choice: ");
        }
    }
    public void checkBalance(){
        System.out.println("Balance:"+Balance);
        menu();
 
    }
    public void withdrawMoney(){
        System.out.println("Enter amount to Withdraw:");
        Scanner sc=new Scanner(System.in);
        float amount=sc.nextFloat();
        if(amount>Balance){
            System.out.println("Insufficient Balance:");
        }
        else{
            Balance = Balance - amount;
          System.out.println("Money Withdraw Successful");
        }
        menu();
    }
    public void depositeMoney(){
        System.out.println("Enter a amount to deposite:");
        Scanner sc=new Scanner(System.in);
        float amount = sc.nextFloat();
        Balance = Balance+amount;
        System.out.println("Deposite succesfully:");
        menu();
    }
}
public class AtmMachine {
    public static void main(String[] args) {
        atm obj=new atm();
        obj.checkpin();
        
    }
}
