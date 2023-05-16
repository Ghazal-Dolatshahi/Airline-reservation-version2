package Menu;
import Data.Flight;
import java.util.Objects;
import java.util.Scanner;

public class FlightField {
    CheckFormat checkFormat = new CheckFormat();

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
        } while (dataBase.flights.search(new Flight(flightId, null, null, null, null, null, null)).size() != 0);
        do {
            System.out.print(" Origin : ");
            origin = input.next();
        } while (!checkFormat.checkLetter(origin));
        do {
            System.out.print("Destination: ");
            destination = input.next();
        } while (!checkFormat.checkLetter(destination) || destination.equals(origin));
        do {
            System.out.print(" Date : ");
            date = input.next();
        } while (!checkFormat.checkDate(date));
        do {
            System.out.print(" Time : ");
            time = input.next();
        } while (!checkFormat.checkTime(time));
        do {
            System.out.print(" Price : ");
            price = input.next();
        } while (checkFormat.parseInteger(price) == null || checkFormat.parseInteger(price) < 0);
        do {
            System.out.print(" Seats : ");
            seats = input.next();
        } while (checkFormat.parseInteger(seats) == null || checkFormat.parseInteger(seats) < 0);

        return new Flight(flightId, origin, destination, date, time, price, seats);
    }
    public Flight flightId(Flight oldFlight , Scanner input , DataBase dataBase) {
        while (true) {
            System.out.print("New FlightId is: ");
            String flightId = input.next();
            if (dataBase.flights.search(new Flight(flightId, null, null, null, null, null, null)).size() == 0) {
                return new Flight(flightId, oldFlight.getOrigin(), oldFlight.getDestination(), oldFlight.getDate(), oldFlight.getTime(), oldFlight.getPrice(), oldFlight.getSeats());
            }
        }
    }
    public Flight origin(Flight oldFlight , Scanner input){
        while (true) {
            System.out.print("New Origin is: ");
            String origin = input.next();
            if(!Objects.equals(origin, oldFlight.getDestination()) && checkFormat.checkLetter(origin))
                return new Flight(oldFlight.getFlightId(), input.next(), oldFlight.getDestination(), oldFlight.getDate(), oldFlight.getTime(), oldFlight.getPrice(), oldFlight.getSeats());
        }
    }
    public Flight destination(Flight oldFlight , Scanner input){
        while (true) {
            String destination = input.next();
            System.out.print("New Destination is: ");
            if(!Objects.equals(destination, oldFlight.getOrigin()) && checkFormat.checkLetter(destination))
                return new Flight(oldFlight.getFlightId(), oldFlight.getOrigin(), input.next(), oldFlight.getDate(), oldFlight.getTime(), oldFlight.getPrice(), oldFlight.getSeats());
        }
    }
    public Flight date(Flight oldFlight , Scanner input){
        while (true) {
            System.out.print("New Date is: ");
            String date = input.next();
            if (checkFormat.checkDate(date))
                return new Flight(oldFlight.getFlightId(), oldFlight.getOrigin(), oldFlight.getDestination(),date, oldFlight.getTime(), oldFlight.getPrice(), oldFlight.getSeats());
        }
    }
    public Flight time(Flight oldFlight , Scanner input){
        while (true) {
            System.out.print("New Time is: ");
            String time = input.next();
            if (checkFormat.checkTime(time))
                return new Flight(oldFlight.getFlightId(), oldFlight.getOrigin(), oldFlight.getDestination(), oldFlight.getDate(),time, oldFlight.getPrice(), oldFlight.getSeats());
        }
    }
    public Flight price(Flight oldFlight , Scanner input){
        while (true) {
            System.out.print("New Price is: ");
            String price = input.next();
            if (checkFormat.parseInteger(price) != null && checkFormat.parseInteger(price) >= 0)
                return new Flight(oldFlight.getFlightId(), oldFlight.getOrigin(), oldFlight.getDestination(), oldFlight.getDate(), oldFlight.getTime(), price, oldFlight.getSeats());
        }
    }
    public Flight seats(Flight oldFlight , Scanner input){
        while (true) {
            System.out.print("New Seats is: ");
            String seats = input.next();
            if (checkFormat.parseInteger(seats) != null && checkFormat.parseInteger(seats) >= 0)
                return new Flight(oldFlight.getFlightId(), oldFlight.getOrigin(), oldFlight.getDestination(), oldFlight.getDate(), oldFlight.getTime(), oldFlight.getPrice(), seats);
        }
    }
}
