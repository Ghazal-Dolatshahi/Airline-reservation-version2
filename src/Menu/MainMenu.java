package Menu;
import Data.Admin;
import Data.Passenger;

import java.util.Objects;
import java.util.Scanner;

public class MainMenu {

    PassengerMenu passengerMenu = new PassengerMenu();
    AdminMenu adminMenu = new AdminMenu();
    CheckFormat checkFormat = new CheckFormat();
    public void menu (DataBase dataBase , Scanner input){
        boolean bool = true;

        while (bool) {
            printTitel("Welcome to airline reservation system");
            Enums.MainMenu.printMenu();
            int command = checkFormat.parseInteger(input.next());
            if(command != 0  && command > -1 &&  command < 4 ) {
                Enums.MainMenu option = Enums.MainMenu.value(command);
                switch (Objects.requireNonNull(option)) {
                    case SIGN_IN -> {signIn(dataBase , input);bool = false;}
                    case SIGN_UP -> {signUp(dataBase , input);bool = false;}
                    case EXIT -> bool = false;
                    default -> System.out.println("\033[91mInvalid!\u001b[0m please choose a number again");
                }
            }
        }
    }
    public void signIn(DataBase database , Scanner input) {
        printTitel("Sign in");
        System.out.print("Enter user name : ");
        String userName = input.next();
        System.out.print("Enter password : ");
        String password = input.next();
            Passenger passenger = new Passenger(userName, password, "0");
            Admin admin = new Admin(userName, password, "0");
            passenger = database.passengers.search(passenger);

            if (passenger != null) {
                passengerMenu.passengerMenu(database, passenger, input);
            } else if (database.admins.search(admin) != null) {
                adminMenu.adminMenu(database, input);
            } else {
                System.out.println("Not user found!");
            }
                menu(database, input);
            }
    public void signUp(DataBase dataBase , Scanner input){
        printTitel("Sign up");
        System.out.print("Enter user name : ");
        String userName = input.next();
        System.out.print("Enter password : ");
        String password = input.next();
        if (checkValid(userName, password , dataBase)) {
            Passenger passenger = new Passenger(userName, password, "0");
            dataBase.passengers.add(passenger);
            System.out.println("Your sign up was successful ✔");
//            String command = input.next();
//            while (!Objects.equals(command, "1") || checkFormat.parseInteger(command) == null) {
//                System.out.println("\033[91mInvalid number!\u001b[0m Please choose a number again \n 1- Back ");
//                command = input.next();
//            }
        }else{
            System.out.println("This passenger exist");
        }
       menu(dataBase , input);

    }
    public static  void printTitel(String text){
        System.out.println("\033[94m---------- ✈ \u001b[0m " + text + " \033[94m✈ -------------\u001b[0m");

    }
    public boolean checkValid(String userName , String password , DataBase dataBase){
        Passenger passenger = new Passenger(userName , password , null);
        return dataBase.passengers.search(passenger) == null;
    }
}
