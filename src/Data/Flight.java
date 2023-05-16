package Data;

import java.util.Arrays;
import java.util.stream.Collectors;
public class Flight {
    private String flightId;
    private String origin;
    private String destination;
    private String time;
    private String seats;
    private String price;
    private String date;

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPrice() {
        return price;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getFlightId() {
        return flightId;
    }

    public Flight(String flightId, String origin, String destination, String date, String time, String price, String seats) {
        this.flightId = flightId;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
        this.seats = seats;
    }
    public boolean similar(Flight newflight) {

            return (newflight.getOrigin() == null || newflight.getOrigin().equals(this.getOrigin())) &&
                    (newflight.getDestination() == null || newflight.getDestination().equals(this.getDestination())) &&
                    (newflight.getDate() == null || newflight.getDate().equals(this.getDate())) &&
                    (newflight.getTime() == null || newflight.getTime().equals(this.getTime())) &&
                    (newflight.getPrice() == null || newflight.getPrice().equals(this.getPrice())) &&
                    (newflight.getSeats() == null || newflight.getSeats().equals(this.getSeats())) &&
                    (newflight.getFlightId() == null || newflight.getFlightId().equals(this.getFlightId()))
                    ;
    }

    public void update(Flight newFlight) {
        this.flightId = newFlight.flightId;
        this.origin = newFlight.origin;
        this.destination = newFlight.destination;
        this.date = newFlight.date;
        this.time = newFlight.time;
        this.price = newFlight.price;
        this.seats = newFlight.seats;
    }

    @Override
    public String toString() {
        return Arrays.stream(this.getClass().getDeclaredFields()).map(field -> {
            try {
                return field.get(this) + "\t   \u001b[35m|\u001b[0m \t";
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining())+  "\u001b[35m-\u001b[0m".repeat(109);
    }
    }