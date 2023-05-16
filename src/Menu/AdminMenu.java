package Menu;
import Data.Flight;
import Data.Passenger;
import Data.Ticket;
import Enums.FlightField;
import java.util.Objects;
import java.util.Scanner;

public class AdminMenu {
    CheckFormat checkFormat = new CheckFormat();
    Menu.FlightField choice = new Menu.FlightField();
    public void adminMenu(DataBase dataBase, Scanner input) {

        boolean bool = true;
        while (bool) {
            Enums.AdminMenu.printMenu();
            MainMenu. printTitel("Admin menu");
            int command = checkFormat.parseInteger(input.next());
            if (command < 5 && command > -1 ) {
                Enums.AdminMenu option = Enums.AdminMenu.value(command);
                switch (Objects.requireNonNull(option)) {
                    case SIGN_OUT -> bool = false;
                    case ADD -> add(input , dataBase);
                    case REMOVE -> remove(input , dataBase);
                    case UPDATE -> update(input , dataBase);
                    case FLIGHT_SCHEDULES -> flightScheduels(dataBase);
                    default -> System.out.println("Please Enter again");
                }
            }
        }
    }

    public void add(Scanner input, DataBase dataBase) {
        MainMenu.printTitel("add Flight");
        Flight newFlight = choice.field(input , dataBase);
        dataBase.flights.add(newFlight);
        System.out.println("Add Flight successfully!");
    }

    public void remove(Scanner input, DataBase dataBase) {
        System.out.println("Flight id :");
        String flightId = input.next();
      if(removeOption(flightId , dataBase))
        System.out.println("Remove Flight successfully!");
       else
           System.out.println("cant find");
    }

    public boolean removeOption(String flightId , DataBase dataBase ) {
        Flight newFlight = new Flight(flightId, null, null, null, null, null, null);
        Ticket ticket = new Ticket(0,new Passenger(null , null , null), new Flight(flightId, null, null, null, null, null, null));
        dataBase.tickets.remove(ticket);

        return dataBase.flights.remove(newFlight);
    }
    public void update(Scanner input, DataBase dataBase) {
        System.out.println("Enter the flight id:");
        String flightId = input.next();
        updateOption(flightId , dataBase , input);
        System.out.println("Update Flight successfully!");

    }
    public void updateOption(String flightId , DataBase dataBase , Scanner input){
        Flight newFlight = new Flight(flightId, null , null , null , null , null , null);
        Flight oldFlight =  dataBase.flights.search(newFlight).get(0);
        newFlight = chooseField(input , oldFlight, dataBase);
        dataBase.flights.update(oldFlight , newFlight);
    }
    public Flight chooseField(Scanner input , Flight oldFlight, DataBase dataBase) {
        while (true) {
            FlightField.printMenu();
            int command = checkFormat.parseInteger(input.next());
            if (command > 0 && command < 8) {
                FlightField option = FlightField.value(command);
                switch (Objects.requireNonNull(option)) {
                    case FLIGHT_ID -> {
                        return choice.flightId(oldFlight , input,dataBase);
                    }
                    case ORIGIN -> {
                        return choice.origin(oldFlight , input);
                    }
                    case DESTINATION -> {
                        return choice.destination(oldFlight, input);
                    }
                    case DATE -> {
                        return choice.date(oldFlight, input);
                    }
                    case TIME -> {
                        return choice.time(oldFlight , input);
                    }
                    case PRICE -> {
                        return choice.price(oldFlight, input);
                    }
                    case SEATS -> {
                        return choice.seats(oldFlight, input);
                    }
                    default -> System.out.print("Enter again:");
                }
            }
        }
    }
    public void flightScheduels(DataBase dataBase) {
        System.out.println(dataBase.flights);
    }
}