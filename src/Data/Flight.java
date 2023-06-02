package Data;

import java.util.Objects;

public class Flight {
    private final String flightId;
    private final String origin;
    private final String destination;
    private final String time;
    private final String seats;
    private final String price;
    private final String date;

    public Flight(String flightId, String origin, String destination, String date, String time, String price, String seats) {
        this.flightId = flightId;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.price = price;
        this.seats = seats;
    }

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
    public String getFlightId() {
        return flightId;
    }

     static Generator<Flight> generator = new Generator<>() {
        @Override
        public Flight generateToRead(String... values) {
            return new Flight(
                    values[0],
                    values[1],
                    values[2],
                    values[3],
                    values[4],
                    values[5],
                    values[6]
            );
        }
        @Override
        public String generateToWrite(Flight element) {
            String str = "";
            StringBuilder strBuilder = new StringBuilder(str);
            strBuilder.append(FileWriter.fixStringToWrite(element.getFlightId()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getOrigin()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getDestination()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getDate()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getTime()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getPrice()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getSeats()));

            return strBuilder.substring(0);
        }
        @Override
        public String fileAddress() {
            return "Flight file.dat";
        }
        @Override
        public int recordSize() {
            return 280;
        }
    };
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(flightId, flight.flightId) && Objects.equals(origin, flight.origin) && Objects.equals(destination, flight.destination) && Objects.equals(time, flight.time) && Objects.equals(seats, flight.seats) && Objects.equals(price, flight.price) && Objects.equals(date, flight.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, origin, destination, time, seats, price, date);
    }

    @Override
    public String toString() {
        return
                flightId+ "\t\t" + origin+"\t\t" + destination+"\t\t" + time+"\t\t" + seats+ "\t\t" + price+ "\t\t" + date + "\t\t" ;
    }
}