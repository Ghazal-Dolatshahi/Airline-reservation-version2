package Menu;
import Data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class PassengerMenu {
    private final CheckFormat checkFormat = new CheckFormat();

    public void userMenu(DataBase dataBase, Passenger passenger, Scanner input) throws IOException {

        boolean bool2 = true;
        while (bool2) {
            MainMenu.printTitle(" Passenger menu");
            Enums.PassengerMenu.printMenu();
            int command = checkFormat.parseInteger(input.next());

            if (command > -1 && command < 7) {
                Enums.PassengerMenu option = Enums.PassengerMenu.value(command);
                switch (Objects.requireNonNull(option)) {
                    case SIGN_OUT -> bool2 = false;
                    case ADD_CHARGE -> addCharge(passenger, input, dataBase);
                    case BOOKED_TICKET -> bookedTicket(dataBase, passenger);
                    case BOOKING_TICKET -> bookingTicket(dataBase, passenger, input);
                    case CHANGE_PASSWORD -> changePassword(input, dataBase , passenger);
                    case TICKET_CANCELLATION -> ticketCancellation(dataBase, passenger, input);
                    case SEARCH_FLIGHT_TICKETS -> search(dataBase, input);
                    default -> System.out.print("\033[91mInvalid number!\u001b[0m please choose a number again\n");
                }
            }
        }
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">add charge </span>
     * @param passenger the passenger that sign in
     */
    public void addCharge(Passenger passenger, Scanner input, DataBase dataBase) throws IOException {

        while (true) {
            MainMenu.printTitle("Add charge");
            System.out.print("Enter charge (more than one):");
            int charge = checkFormat.parseInteger(input.next());

            if (charge > 1) {
                updateData(null, dataBase, charge, passenger.getUsername());
                System.out.println("Your charge has been successfully added ✔ \n Your charge is : " + dataBase.passengers.getPassengersFile().fixStringToRead(80 + dataBase.passengers.searchIndex(0, passenger.getUsername())));
                break;

            } else
                System.out.println("\033[91mInvalid!\u001b[0m try again");
        }
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">show the tickets reserved by the user </span>
     * @param passenger the passenger that sign in
     */
    public void bookedTicket(DataBase dataBase, Passenger passenger) {

        MainMenu.printTitle("Booked ticket");
        System.out.println("TicketId\tUserName \tFlightId \t   Origin \t   Destination\t\t\t Time \t\t Seats \t\t   Price \t\t\t  Date");
        System.out.println("-".repeat(150));

        for (int i = 0; i < dataBase.tickets.search(40, passenger.getUsername()).size(); i++) {
            System.out.println(dataBase.tickets.search(40, passenger.getUsername()).get(i));
            System.out.println("-".repeat(150));
        }
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Get information to book tickets</span>
     * @param passenger the passenger that sign in
     */
    public void bookingTicket(DataBase dataBase, Passenger passenger, Scanner input) throws IOException {

        MainMenu.printTitle("Booking ticket");
        System.out.print("Enter the flight id :");
        String flightId = input.next();

        int ticketId = bookingOption(dataBase, passenger, flightId);
        if (ticketId != 0)
            System.out.println("Your ticket id is : " + ticketId);
        else
            System.out.println("You cant book it!");
    }

    /**
     * @param passenger the passenger that sign in
     * @param flightId flight id of the flight that user wants to book
     * @return the ticket id
     */
    public Integer bookingOption(DataBase dataBase, Passenger passenger, String flightId) throws IOException {

        if (dataBase.flights.search(0, flightId).size() != 0) {
            Flight foundFlight = dataBase.flights.search(0, flightId).get(0);

            if(updateData(flightId , dataBase , 0 , passenger.getUsername())){
                Ticket ticket = new Ticket(dataBase.tickets.findMaxId(), passenger, foundFlight);
                dataBase.tickets.write(ticket);
                updateData(flightId, dataBase, -1, passenger.getUsername());
                return ticket.getTicketId();

            } else
                return 0;
        } else
            return 0;
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Get the new password and change it</span>
     */
    public void changePassword(Scanner input, DataBase dataBase ,Passenger passenger) throws IOException {

        MainMenu.printTitle("Change password");
        System.out.print("change password:");
        String newPassword = input.next();

        if (passenger.getUsername().equals("admin")) {
            System.out.println("you can't change your password");

        } else {

            long i = dataBase.passengers.searchIndex(0, passenger.getUsername());
            dataBase.passengers.update(dataBase.passengers.getPassengersFile().fixStringToRead(40 + i), newPassword, 40);
            System.out.println("your password successfully changed!");
        }
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Get the ticket id and cancel it</span>
     * @param passenger the passenger that sign in
     */
    public void ticketCancellation(DataBase dataBase, Passenger passenger, Scanner input) throws IOException {

        MainMenu.printTitle("ticket cancellation");
        System.out.print("Enter the Ticket id :");
        String ticketId = input.next();

        if (dataBase.tickets.search(0, ticketId, passenger.getUsername()).size() != 0) {
            Ticket ticket = dataBase.tickets.search(0, ticketId, passenger.getUsername()).get(0);
            updateData(ticket.getFlight().getFlightId(), dataBase, +1, passenger.getUsername());
            dataBase.tickets.remove(ticket);
            System.out.println("This ticket cancelled successfully!");
        } else
            System.out.println("Not ticket found with this ticket ID");
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Search flights by user</span>
     */
    public void search(DataBase dataBase, Scanner input) {

        MainMenu.printTitle("Search flight");

        System.out.print("origin: ");
        String origin = input.next();
        schedules(dataBase.flights.search(20, origin));

        System.out.print("Destination: ");
        String destination = input.next();
        schedules(dataBase.flights.search(20, origin, destination));

        System.out.print("Date: ");
        String date = input.next();
        schedules(dataBase.flights.search(20, origin, destination, date));

        System.out.print("Time: ");
        String time = input.next();
        schedules(dataBase.flights.search(20, origin, destination, date, time));

        System.out.print("Price: ");
        String price = input.next();
        schedules(dataBase.flights.search(20, origin, destination, date, time, price, price));
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">show flights</span>
     * @param flights the list of flights to be displayed
     */
    public void schedules(ArrayList<Flight> flights) {

        System.out.println("FlightId \t Origin \tDestination Time \t Seats \t    Price \t      Date");
        System.out.println("-".repeat(100));

        for (Flight flight : flights) {
            System.out.println(flight);
            System.out.println("-".repeat(100));
        }
    }

    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">update seats and charge</span>
     * @param flightId flight id of the flight that seats needs to be updated
     * @param update new value
     * @param userName the username of the user whose charge needs to be updated
     */
    public boolean updateData(String flightId, DataBase dataBase, int update, String userName) throws IOException {

        long recordNumber = dataBase.flights.searchIndex(0, flightId);

        if (update == 1 || update == -1) {
            dataBase.flights.update(dataBase.flights.getFlightsFile().fixStringToRead(recordNumber + 240), String.valueOf((Integer.parseInt(dataBase.flights.getFlightsFile().fixStringToRead(recordNumber + 240)) + (update))), 240);
            dataBase.passengers.update(dataBase.passengers.getPassengersFile().fixStringToRead(80 + dataBase.passengers.searchIndex(0, userName)), String.valueOf(Integer.parseInt(dataBase.passengers.getPassengersFile().fixStringToRead(80 + dataBase.passengers.searchIndex(0, userName))) + ((Integer.parseInt(dataBase.flights.getFlightsFile().fixStringToRead(recordNumber + 200))) * update)), 80);
        } else
            dataBase.passengers.update(dataBase.passengers.getPassengersFile().fixStringToRead(80 + dataBase.passengers.searchIndex(0, userName)), String.valueOf(Integer.parseInt(dataBase.passengers.getPassengersFile().fixStringToRead(80 + dataBase.passengers.searchIndex(0, userName))) + update), 80);

        return (Integer.parseInt(dataBase.flights.getFlightsFile().fixStringToRead(recordNumber + 200)) <= (Integer.parseInt(dataBase.passengers.getPassengersFile().fixStringToRead(80 + dataBase.passengers.searchIndex(0, userName)))) && ((Integer.parseInt(dataBase.flights.getFlightsFile().fixStringToRead(recordNumber + 240))) > 0));

        }
}
