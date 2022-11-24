import java.util.*;
public class Main{
    static Scanner scan = new Scanner(System.in);
    public static List<User>usersList= new ArrayList<>();
    
    //*******************Creating Account**********************
    public static void createAccount(){
        scan.nextLine();
        System.out.println("Create Account");
        System.out.println();
        System.out.println("Enter your username: ");
        String uname=scan.nextLine();
        boolean flag=false;
        for(User i : usersList){
            if(i.name.equals(uname)){
                System.out.println("UserName already exists");
                flag=true;
                break;
            }
        }
        if(flag==false){
        System.out.println("Enter your Password: ");
        int upass=scan.nextInt();
        scan.nextLine();
        System.out.println("Enter your email: ");
        String uemail=scan.nextLine();
        System.out.println("Enter the amount: ");
        int uamount=scan.nextInt();
        User us = new User(uname,upass,uemail,uamount);
        usersList.add(us);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Account created successfully");
        System.out.println();
        }
    }
    
    //*********************Paying Debt*************************
    public static void payDebt(int i){
        System.out.println("Pay Debt");
        System.out.println();
        System.out.println("Enter the amount to be paid : ");
        int expenses = scan.nextInt();
        int sharing_number=usersList.get(i).friend.size();
        if(sharing_number==1){
            if(expenses<=usersList.get(i).amount){
            usersList.get(i).amount-=expenses;
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("The amount from your account "+expenses+"is debited .The balance amount is "+usersList.get(i).amount);
            }
            else{
                System.out.println("Amount is Not sufficient\nPlease add the amount");
            }
        }
        else{
            int sharing_amount=expenses/sharing_number; 
            usersList.get(i).amount-=sharing_amount;
            if(sharing_amount<=usersList.get(i).amount){
            for(int j =0;j<usersList.get(i).friend.size();j++){
                for(int k=0;k<usersList.size();k++){
                    if(usersList.get(i).friend.get(j).equals(usersList.get(k).name)){
                        usersList.get(k).amount-=sharing_amount;
                        break;
                    }
                }
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("The sharing amount from your account "+sharing_amount+" is debited .The balance amount is "+usersList.get(i).amount);
            }
            else{
                System.out.println("Amount is Not sufficient\nPlease add the amount");
            }
        }
    }
    
    //********************Remove Friend************************
    public static void removeFriend(int i){
        scan.nextLine();
        System.out.println("Remove Friend");
        System.out.println();
        System.out.println("Enter Your friend's username : ");
        String friend_name = scan.nextLine();
        boolean flag=false;
        for(int k=0;k<usersList.get(i).friend.size();k++){
            if(friend_name.equals(usersList.get(i).friend.get(k))){
                usersList.get(i).friend.remove(k);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Friend removed Successfully");
                flag=true;
                break;
            }
        }
        if(flag==false){
            System.out.println("No User Found");
        }
    }
    
    //********************Add Friend***************************
    public static void addFriend(int i){
        scan.nextLine();
        System.out.println("Add Friend");
        System.out.println();
        System.out.println("Enter Your friend's username : ");
        String friend_name = scan.nextLine();
        boolean flag=false;
        for(int j=0;j<usersList.size();j++){
            if(friend_name.equals(usersList.get(j).name)){
                usersList.get(i).friend.add(friend_name);
                usersList.get(j).friend.add(usersList.get(i).name);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Friend added successfully...");
                flag=true;
                break;
            }
        }
        if(flag==false){
            System.out.println("No User Found");
        }
    }
    
    //********************User*********************************
    public static void user(){
        scan.nextLine();
        System.out.println("User Login");
        System.out.println();
        System.out.println("Enter username :");
        String usrname=scan.nextLine();
        System.out.println("Enter user password");
        int pass = scan.nextInt();
        boolean flag=false;
        for(int i=0;i<usersList.size();i++){
            if((usersList.get(i).name.equals(usrname))&&(usersList.get(i).password==pass)){
                System.out.println("Welcome "+usersList.get(i).name);
                while(true){
                    System.out.println("1.Add friend");
                    System.out.println("2.Remove friend");
                    System.out.println("3.Expenses and Debt payment");
                    System.out.println("4.Friends list");
                    System.out.println("5.Wallet");
                    System.out.println("6.Exit");
                    System.out.println("Enter your choice:");
                    int userchoice=scan.nextInt(); 
                    if(userchoice==1){
                        addFriend(i);
                    }
                    else if(userchoice==2){
                        removeFriend(i);
                    }
                    else if(userchoice==3){
                        payDebt(i);
                    }
                    else if(userchoice==4){
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        for(int j=0;j<usersList.get(i).friend.size();j++){
                            System.out.println(usersList.get(i).friend.get(j));
                        }
                    }
                    else if(userchoice==5){
                        System.out.println("Your balance is : "+usersList.get(i).amount);
                        System.out.println("1.Add Amount");
                        System.out.println("2.Go Back");
                        int choice1=scan.nextInt();
                        if(choice1==1){
                            System.out.println("Enter the amount to be added");
                            int added_amount=scan.nextInt();
                            usersList.get(i).amount+=added_amount;
                            System.out.println("Amount added Successfully");
                        }
                        else{
                            continue;
                        }
                    }
                    else if(userchoice==6){
                        break;
                    }
                    else{
                        System.out.println("Invalid choice");
                    }
                    
                }
                flag=true;
                break;
            }
        }
        if(flag==false){
            System.out.println("No User Found");
        }
    }
    
    //********************Main*********************************
    public static void main(String[] args){
        System.out.println("WELCOME TO SPLIT WISE");
          while(true){
                System.out.println("1.Create account");
                System.out.println("2.login ");
                System.out.println("3.Exit");
                System.out.println("Enter your choice");
                int userchoie = scan.nextInt();
                if(userchoie==1){
                    createAccount();
                }else if(userchoie==2){
                    user();
                }else if(userchoie==3){
                    System.out.println("THANK YOU");
                    break;
                }else{
                    System.out.println("Invalid input");
                }
          }
    }
}
import java.util.*; 
public class User{
    String name;
    int password;
    String email;
    int amount;
    List<String>friend=new ArrayList<>();
    public User(String name,int password,String email,int amount){
        this.name=name;
        this.password=password;
        this.email=email;
        this.amount=amount;
        friend.add(" ");
    }
}
