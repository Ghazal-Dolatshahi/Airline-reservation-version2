import Menu.DataBase;
import Menu.MainMenu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        DataBase dataBase = new DataBase();
        MainMenu mainMenu = new MainMenu();
        mainMenu.menu(dataBase , input);
    }
}