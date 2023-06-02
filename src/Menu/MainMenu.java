package Menu;
import Data.Admin;
import Data.Passenger;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class MainMenu {
    /**
     * <h1 style = "font-family : Times New Roman ; color:#20B2AA">Menu method :</h1>
     *
     * <pre style = "font-family : Times New Roman ; font-size :12px ;color:#20B2AA">
     * Enter 1 to sign in   <span style=" font-size :12px ; font-family : Times New Roman">{@link #signIn(DataBase, Scanner)}</span><hr>
     * Enter 2 to sign up    <span style=" font-size :12px ; font-family : Times New Roman">{@link #signUp(DataBase, Scanner)}</span><hr>
     * Enter 3 to Exit <hr></pre>
     *
     *     <img src = "../Pics/air-plane.jpg" height = "300" width = "620">
     *    */

  private final PassengerMenu passengerMenu = new PassengerMenu();
  private final  AdminMenu adminMenu = new AdminMenu();
   private final CheckFormat checkFormat = new CheckFormat();

    public void menu (DataBase dataBase , Scanner input) throws IOException {
        boolean bool = true;

        while (bool) {
            printTitle("Welcome to airline reservation system");
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
    /**
     * <span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Users enter their password and username and sign in </span>
     */
    public void signIn(DataBase database , Scanner input) throws IOException {

        printTitle("Sign in");

        System.out.print("Enter user name : ");
        String userName = input.next();

        System.out.print("Enter password : ");
        String password = input.next();

           if(!signInOption(userName , password , database , input))
               System.out.println("Not user found!");

           menu(database, input);
            }

    /**
     *
     * @param userName the username that user entered by the user
     * @param password the password that entered by the user
     * @return true if the signup is successful ,otherwise return false
     */
            public boolean signInOption(String userName , String password , DataBase database , Scanner input) throws IOException {

                Passenger passenger = new Passenger(userName, password, "0");
                Admin admin = new Admin(userName, password, "0");
                passenger = database.passengers.search(passenger);

                if (passenger != null) {
                    passengerMenu.userMenu(database, passenger, input);
                    return true;

                } else if (database.admins.search(admin) != null) {
                    adminMenu.userMenu(database, admin, input);
                    return true;

                }else
                return false;
            }

    /**
     * <span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Each passenger chooses her/his password and username and sign up </span>
     */
    public void signUp(DataBase dataBase , Scanner input) throws IOException {

        printTitle("Sign up");

        System.out.print("Enter user name : ");
        String userName = input.next();

        System.out.print("Enter password : ");
        String password = input.next();

        if (checkValid(userName, dataBase)) {
            Passenger passenger = new Passenger(userName, password, "0");
            dataBase.passengers.write(passenger);
            System.out.println("Your sign up was successful ✔");

        }else{
            System.out.println("This passenger exist");
        }

       menu(dataBase , input);

    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">printing a text in a specific format </span>
     * @param text the text we want to print
     */
    public static void printTitle(String text){
        System.out.println("\033[94m---------- ✈ \u001b[0m " + text + " \033[94m ✈ -------------\u001b[0m");

    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">checks whether there is a user with this username and password or not </span>
     * @param userName that entered by user
     * @param password that entered by user
     * @return true if a user with this profile is not found , otherwise return false
     */
    public boolean checkValid(String userName , DataBase dataBase){

        Passenger passenger = new Passenger(userName , null , null);
        Admin admin = new Admin(userName , null , null);

        return (dataBase.passengers.search(passenger) == null) && (dataBase.admins.search(admin) == null);

    }
}
