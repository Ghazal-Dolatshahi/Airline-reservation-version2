package Data;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Ticket {
    private final int ticketId;
    private static int counter = 1000;
    private final Passenger passenger;
    private final Flight flight;

    public int getTicketId() {
        return ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Ticket(Passenger passenger, Flight flight) {
        this.passenger = passenger;
        this.flight = flight;
        this.ticketId = counter++;
    }

    public Ticket(int ticketId, Passenger passenger, Flight flight) {
        this.passenger = passenger;
        this.flight = flight;
        this.ticketId = ticketId;
    }

    public boolean similar(Ticket newTicket) {
        return (((newTicket.getFlight().equals(this.getFlight())) || (newTicket.getFlight().getFlightId()== null) || (newTicket.getFlight().getFlightId().equals(this.getFlight().getFlightId()))||
                 (newTicket.getFlight() == null)) ||
                ((newTicket.getPassenger().equals(this.getPassenger())) || (newTicket.getPassenger() == null)) &&
                        ((newTicket.getTicketId() == this.getTicketId() || newTicket.getTicketId() == 0)));
    }

    public String toString() {
        return Arrays.stream(this.getClass().getDeclaredFields()).map(field -> {
            try {
                return field.get(this) + "\t\t";
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining());
    }
//        System.out.printf("\u001b[35m|\u001b[0m%-10s\u001b[35m|\u001b[0m%-10s\u001b[35m|\u001b[0m%-10s\u001b[35m|\u001b[0m%-12s\u001b[35m|\u001b[0m%-12s\u001b[35m|\u001b[0m%-8s\u001b[35m|\u001b[0m%-10s\u001b[35m|\u001b[0m%-8s\u001b[35m|\u001b[0m%-50s %n", "TicketId", "FlightId", "Origin", "Destination", "Date", "Time", "Price", "Seats", "Explanation");

//        for (int i = 0; i < ticketL.size(); i++) {
//            if (Objects.equals(database.tickets.ticket.get(i).getPassenger().getUserName(), userName)) {
//                System.out.printf("\u001b[35m|\u001b[0m%-10s\u001b[35m|\u001b[0m%-10s\u001b[35m|\u001b[0m%-10s\u001b[35m|\u001b[0m%-12s\u001b[35m|\u001b[0m%-12s\u001b[35m|\u001b[0m%-8s\u001b[35m|\u001b[0m%-10s\u001b[35m|\u001b[0m%-8s\u001b[35m|\u001b[0m%-50s %n", database.tickets.ticket.get(i).getTicketId(),
//                        database.tickets.ticket.get(i).getFlight().getFlightId(), database.tickets.ticket.get(i).getFlight().getOrigin(),
//                        database.tickets.ticket.get(i).getFlight().getDestination(), database.tickets.ticket.get(i).getFlight().getDate(),
//                        database.tickets.ticket.get(i).getFlight().getTime(), database.tickets.ticket.get(i).getFlight().getPrice(),
//                        database.tickets.ticket.get(i).getFlight().getSeats(), database.tickets.ticket.get(i).getExplanation());
            }