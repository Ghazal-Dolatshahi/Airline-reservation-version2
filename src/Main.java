import Menu.DataBase;
import Menu.MainMenu;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    /**
     <h2><span style="font-style:italic; color: #20B2AA "> ✈  Airline reservation system ✈ </h2>
     @author <span style="font-style:italic; color:#7B68EE"><b>Ghazal Dolatshahi</b></span><hr>
      *<span style="color:#7B68EE">Email:</span>
      * <span style="color: #778899 ">  <a href="mailto:ghazalldolatshahi.p@gmail.com">(ghazalldolatshahi.p@gmail.com)</a><hr>
      * <span style="color:#7B68EE ">LinkedIn: </span>
      * <span style="color:#778899"> <a href="https://www.linkedin.com/in/ghazal-dolatshahi-433b73269/">Ghazal.LinkedIn(Ghazal Dolatshahi)</a><hr>
      * @since <span style="font-family: Times New Roman; color:#7B68EE"> 21 April 2023</span>
     */
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        DataBase dataBase = new DataBase();
        MainMenu mainMenu = new MainMenu();
         mainMenu.menu(dataBase , input);
    }
}