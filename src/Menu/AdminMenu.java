package Menu;
import Data.Flight;
import Data.Passenger;
import Enums.FlightField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AdminMenu extends PassengerMenu{
  private final CheckFormat checkFormat = new CheckFormat();
  private final Menu.FlightField flightField = new Menu.FlightField();

    @Override
    public void userMenu(DataBase dataBase, Passenger passenger, Scanner input) throws IOException {

        boolean bool = true;
        while(bool) {
            System.out.println("passenger or admin ?");
            String choose = input.next();

            switch (choose) {
                case "passenger" -> {super.userMenu(dataBase, passenger, input); bool = false;}
                case "admin" -> {adminMenu(dataBase , input) ;bool = false;}
                default -> System.out.println("Please choose again");
            }
        }
    }
    public void adminMenu(DataBase dataBase , Scanner input) throws IOException {

        boolean bool = true;
        while (bool) {
            MainMenu.printTitle("Admin menu");
            Enums.AdminMenu.printMenu();
            int command = checkFormat.parseInteger(input.next());
            if (command < 5 && command > -1) {
                Enums.AdminMenu option = Enums.AdminMenu.value(command);

                switch (Objects.requireNonNull(option)) {
                    case SIGN_OUT -> bool = false;
                    case ADD -> add(input, dataBase);
                    case REMOVE -> remove(input, dataBase);
                    case UPDATE -> update(input, dataBase);
                    case FLIGHT_SCHEDULES -> flightSchedules(dataBase);
                    default -> System.out.println("Please Enter again");
                }
            }
        }
    }

    /**
     * <span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">add flight</span>
     * @see Menu.FlightField
     */
    public void add(Scanner input, DataBase dataBase) throws IOException {

        MainMenu.printTitle("Add Flight");
        Flight newFlight = flightField.field(input , dataBase);
        dataBase.flights.write(newFlight);
        System.out.println("Flight added successfully!");
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Remove flight base on flight id</span>
     */
    public void remove(Scanner input, DataBase dataBase) throws IOException {

        MainMenu.printTitle("Remove Flight");
        System.out.print("Flight id :");
        String flightId = input.next();
      if(dataBase.flights.remove(flightId))
        System.out.println("The flight was successfully deleted!");
       else
           System.out.println("No flight found with this flight ID");
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Update each field of flight</span>
     */
    public void update(Scanner input, DataBase dataBase) throws IOException {

        MainMenu.printTitle("Update Flight");
        System.out.println("Enter the flight id:");
        String flightId = input.next();
       if(updateOption(flightId , dataBase , input))
        System.out.println("The flight was successfully updated!");
       else
           System.out.println("Cant update it \n");
    }

    /**
     *
     * @param flightId flight id of flight that admin wants to update
     */
    public boolean updateOption(String flightId , DataBase dataBase , Scanner input) throws IOException {
        ArrayList<Flight> flights = dataBase.flights.search(0 , flightId);

        if(flights.size() != 0) {
            Flight oldFlight = dataBase.flights.search(0 ,flightId).get(0);
            return chooseField(input, oldFlight, dataBase);

        }else
            return false;
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Show the fields that admin can update and update it</span>
     * @param oldFlight the flight that admin want to update
     * @see Menu.FlightField
     */
    public boolean chooseField(Scanner input , Flight oldFlight, DataBase dataBase) throws IOException {

            FlightField.printMenu();
            int command = checkFormat.parseInteger(input.next());
            if (command > 0 && command < 8) {
                FlightField option = FlightField.value(command);

                switch (Objects.requireNonNull(option)) {
                    case FLIGHT_ID -> dataBase.flights.update(oldFlight.getFlightId(), flightField.flightId(input , dataBase) , 0 );
                    case ORIGIN -> dataBase.flights.update(oldFlight.getOrigin() , flightField.origin(oldFlight,input),40);
                    case DESTINATION -> dataBase.flights.update(oldFlight.getDestination() , flightField.destination(oldFlight,input),80);
                    case DATE -> dataBase.flights.update(oldFlight.getDate() , flightField.date(input),120);
                    case TIME -> dataBase.flights.update(oldFlight.getTime() , flightField.time(input),160);
                    case PRICE -> dataBase.flights.update(oldFlight.getPrice() , flightField.price(input),200);
                    case SEATS -> dataBase.flights.update(oldFlight.getSeats() ,  flightField.seats(input) , 240);
                    default -> System.out.print("Enter again:");
                }
                return true;
            } else
                return false;
        }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Show flight schedules</span>
     */
    public void flightSchedules(DataBase dataBase) throws IOException {

        System.out.println("FlightId \t Origin \tDestination Time \t Seats \t    Price \t      Date");
        System.out.println("-".repeat(100));

        for (int i = 0; i < dataBase.flights.getFlightsFile().getFile().length(); i += dataBase.flights.getFlightsFile().getGenerator().recordSize()) {
            System.out.println(dataBase.flights.getFlightsFile().read(i));
            System.out.println("-".repeat(100));
        }
    }

    }