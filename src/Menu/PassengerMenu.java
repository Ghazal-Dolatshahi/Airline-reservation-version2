package Menu;
import Data.*;
import java.util.Objects;
import java.util.Scanner;

public class PassengerMenu {
    CheckFormat checkFormat = new CheckFormat();
    public void passengerMenu(DataBase dataBase , Passenger passenger , Scanner input){


      boolean bool2 = true;
      while (bool2) {
          MainMenu.printTitel("Passenger menu");
          Enums.PassengerMenu.printMenu();
          int command = checkFormat.parseInteger(input.next());
          if (command > -1 && command < 7) {
              Enums.PassengerMenu option = Enums.PassengerMenu.value(command);
              switch (Objects.requireNonNull(option)) {
                  case SIGN_OUT -> bool2 = false;
                  case ADD_CHARGE -> addCharge(passenger, input);
                  case BOOKED_TICKET -> bookedTicket(dataBase, passenger);
                  case BOOKING_TICKET -> bookingTicket(dataBase, passenger, input);
                  case CHANGE_PASSWORD -> changePassword(passenger, input);
                  case TICKET_CANCELLATION -> ticketCancellation(dataBase, passenger, input);
                  case SEARCH_FLIGHT_TICKETS -> search(dataBase, input);
                  default -> System.out.print("\033[91mInvalid number!\u001b[0m please choose a number again\n");
              }
          }
      }
    }
    public void addCharge(Passenger passenger , Scanner input) {
        while (true) {
           MainMenu.printTitel("Add charge");
            System.out.print("Enter charge :");
            int charge = checkFormat.parseInteger(input.next());
           if(chargeOption(charge , passenger)){
                System.out.println("Your charge has been successfully added ✔ \nYour charge is :" +  passenger.getCharge());
                break;
            }
            System.out.println("\033[91mInvalid!\u001b[0m try again");
    }
    }
    public boolean chargeOption(int charge , Passenger passenger) {
        if (charge != 0 && charge >= 0) {
            passenger.setCharge(String.valueOf(Integer.parseInt(passenger.getCharge()) + charge));
            return true;
        }else
         return false;
    }
    public void bookedTicket(DataBase dataBase , Passenger passenger ){
        MainMenu.printTitel("Booked ticket");
        Ticket ticket = new Ticket(0, passenger, new Flight(null , null , null , null , null , null , null));
        System.out.println(Tickets.returnString(dataBase.tickets.search(ticket)));
    }
    public void bookingTicket(DataBase dataBase , Passenger passenger , Scanner input) {
        MainMenu.printTitel("Booking ticket");
        System.out.println("Enter the flight id :");
        String flightId = input.next();
        int ticketId = bookingOption(dataBase , passenger , flightId);
        if(ticketId != 0)
            System.out.println("your ticket id is : " + ticketId );
        else
            System.out.println("you cant book it");
    }
    public Integer bookingOption(DataBase dataBase , Passenger passenger , String flightId){
        if(dataBase.flights.search(new Flight(flightId, null, null, null, null, null, null)).size() != 0){
            Flight foundFlight = dataBase.flights.search(new Flight(flightId, null, null, null, null, null, null)).get(0);
            if (Integer.parseInt(foundFlight.getPrice()) <= Integer.parseInt(passenger.getCharge()) && (Integer.parseInt(foundFlight.getSeats()) > 0)) {
            Ticket ticket = new Ticket(passenger, foundFlight);
            dataBase.tickets.add(ticket);
            ticket.getFlight().setSeats(String.valueOf(Integer.parseInt(ticket.getFlight().getSeats()) - 1));
            passenger.setCharge(String.valueOf(Integer.parseInt(passenger.getCharge()) - Integer.parseInt(ticket.getFlight().getPrice())));
            return ticket.getTicketId();
        }else
             return 0;
        }else
           return 0;
    }
    public void changePassword(Passenger passenger , Scanner input){
        MainMenu.printTitel("Change password");
        System.out.println("change password:");
        String newPassword = input.next();
        passenger.setPassword(newPassword);
    }
    public void ticketCancellation(DataBase dataBase , Passenger passenger , Scanner input){
        System.out.println("Enter the Ticket id :");
        Ticket ticket = new Ticket(input.nextInt() ,passenger,new Flight(null , null , null , null , null , null , null));
         ticket = dataBase.tickets.search(ticket).get(0);
         if(ticket != null) {
             cancelOption(ticket , passenger, dataBase);
             System.out.println("ticket cancelled ");
         }
      else
          System.out.println("cant find");
    }
    public void cancelOption(Ticket ticket, Passenger passenger , DataBase dataBase){
        ticket.getFlight().setSeats(String.valueOf(Integer.parseInt(ticket.getFlight().getSeats()) + 1));
        passenger.setCharge(String.valueOf(Integer.parseInt(passenger.getCharge()) + Integer.parseInt(ticket.getFlight().getPrice())));
        dataBase.tickets.remove(ticket);
    }
    public void search(DataBase dataBase , Scanner input){
        MainMenu.printTitel("Search flight");
        System.out.println(dataBase.flights);
        System.out.print("origin: ");
        String origin = input.next();
        System.out.println(Flights.returnString(dataBase. flights.search( new Flight(null , origin, null , null , null , null , null ))));
        System.out.print("Destination: ");
        String destination = input.next();
        System.out.println( Flights.returnString(dataBase. flights.search( new Flight(null, origin , destination , null , null , null , null ))));
        System.out.print("Date: ");
        String date = input.next();
        System.out.println( Flights.returnString(dataBase. flights.search( new Flight(null, origin , destination , date , null , null , null ))));
        System.out.print("Time: ");
        String time = input.next();
        System.out.println( Flights.returnString(dataBase. flights.search( new Flight(null, origin , destination , date , time , null , null ))));
        System.out.print("Price: ");
        String price = input.next();
        Flights.returnString(dataBase. flights.search( new Flight(null, origin , destination , date , time , price, null )));
    }
}
