package Data;

import java.util.Objects;
public class Ticket {
    private final int ticketId ;
    private final Passenger passenger;
    private final Flight flight;

    public Ticket(int ticketId, Passenger passenger, Flight flight) {
        this.passenger = passenger;
        this.flight = flight;
        this.ticketId = ticketId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }
    static Generator<Ticket> generator = new Generator<>() {
        @Override
        public Ticket generateToRead(String... values) {
            return new Ticket(
                    Integer.parseInt(values[0]),
                    new Passenger(values[1]),
                    new Flight(values[2], values[3], values[4], values[5], values[6], values[7], values[8])
            );
        }

        @Override
        public String generateToWrite(Ticket element) {
            String str = "";
            StringBuilder strBuilder = new StringBuilder(str);
            strBuilder.append(FileWriter.fixStringToWrite(String.valueOf(element.getTicketId())));
            strBuilder.append(FileWriter.fixStringToWrite(element.getPassenger().getUsername()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getFlight().getFlightId()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getFlight().getOrigin()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getFlight().getDestination()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getFlight().getDate()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getFlight().getTime()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getFlight().getPrice()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getFlight().getSeats()));

            return strBuilder.substring(0);
        }

        @Override
        public String fileAddress() {
            return "Ticket file.dat";
        }

        @Override
        public int recordSize() {
            return 360;
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketId == ticket.ticketId && Objects.equals(passenger, ticket.passenger) && Objects.equals(flight, ticket.flight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, passenger, flight);
    }
    @Override
    public String toString() {
        return
            ticketId + "\t\t\t" + passenger.getUsername() + "\t\t\t" + flight.getFlightId() + "\t\t\t" + flight.getOrigin() + "\t\t\t" +
                    flight.getDestination()+ "\t\t\t" + flight.getTime() + "\t\t\t" + flight.getSeats() + "\t\t\t" + flight.getPrice() +
                    "\t\t\t" + flight.getDate() + "\t\t\t" ;
    }
}