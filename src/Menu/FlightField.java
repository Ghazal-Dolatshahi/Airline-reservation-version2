package Menu;
import Data.Flight;
import java.util.Objects;
import java.util.Scanner;

public class FlightField {
    CheckFormat checkFormat = new CheckFormat();

    /**
     *get all field of flight for add flight by admin and check their format
     */
    public Flight field(Scanner input , DataBase dataBase) {
    String flightId;
    String origin;
    String destination;
    String date;
    String time;
    String price;
    String seats;

    do {
        System.out.print("FlightId : ");
        flightId = input.next();
    } while (dataBase.flights.search(0 ,flightId).size() != 0);
    do {
        System.out.print("Origin : ");
        origin = input.next();
    } while (!checkFormat.checkLetter(origin));
    do {
        System.out.print("Destination: ");
        destination = input.next();
    } while (!checkFormat.checkLetter(destination) || destination.equals(origin));
    do {
        System.out.print("Date (Format is yyyy/mm/dd): ");
        date = input.next();
    } while (!checkFormat.checkDate(date));
    do {
        System.out.print("Time (Format is --:--): ");
        time = input.next();
    } while (!checkFormat.checkTime(time));
    do {
        System.out.print("Price : ");
        price = input.next();
    } while (checkFormat.parseInteger(price) == null || checkFormat.parseInteger(price) < 0);
    do {
        System.out.print("Seats : ");
        seats = input.next();
    } while (checkFormat.parseInteger(seats) == null || checkFormat.parseInteger(seats) < 0);

    return new Flight(flightId, origin, destination, date, time, price, seats);
}
    /**
     * get flight id and check the format
     */
    public String flightId( Scanner input , DataBase dataBase) {

        while (true) {
            System.out.print("New FlightId is: ");
            String flightId = input.next();
            if (dataBase.flights.search(0,flightId).size() == 0) {
         return flightId;
            }
        }
    }

    /**
     * get origin and check the format
     * @param oldFlight the flight that admin wants to update it
     */
    public String origin(Flight oldFlight , Scanner input){

        while (true) {
            System.out.print("New Origin is: ");
            String origin = input.next();
            if(!Objects.equals(origin, oldFlight.getDestination()) && checkFormat.checkLetter(origin))
                return origin;
        }
    }
    /**
     * get destination and check the format
     * @param oldFlight the flight that admin wants to update it
     */
    public String destination(Flight oldFlight , Scanner input){

        while (true) {
            System.out.print("New Destination is: ");
            String destination = input.next();
            if(!Objects.equals(destination, oldFlight.getOrigin()) && checkFormat.checkLetter(destination))
       return destination;
         }
    }
    /**
     * get date and check the format
     */
    public String date(Scanner input){

        while (true) {
            System.out.print("New Date is: ");
            String date = input.next();
            if (checkFormat.checkDate(date))
     return date;
        }
    }
    /**
     * get time  and check the format
     */
    public String time(Scanner input){

        while (true) {
            System.out.print("New Time is: ");
            String time = input.next();
            if (checkFormat.checkTime(time))
     return time;
        }
    }
    /**
     * get price and check the format
     */
    public String price(Scanner input){

        while (true) {
            System.out.print("New Price is: ");
            String price = input.next();
            if (checkFormat.parseInteger(price) != null && checkFormat.parseInteger(price) >= 0)
      return price;
        }
    }
    /**
     * get the number of seats and check the format
     */
    public String seats(Scanner input){

        while (true) {
            System.out.print("New Seats is: ");
            String seats = input.next();
            if (checkFormat.parseInteger(seats) != null && checkFormat.parseInteger(seats) >= 0)
     return seats;
        }
    }
}
