package Data;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FlightWriter extends FileWriter <Flight> {
    public FlightWriter(Generator generator) throws FileNotFoundException {
        super(generator);
    }

    @Override
    public void removeOption(Flight foundElement) throws IOException {
        super.removeOption(foundElement);
        FileWriter<Ticket> ticket = new FileWriter<>(Ticket.generator);
        ticket.remove(foundElement.getFlightId() , 80);
    }

    @Override
    public void update(String value, int start, String update) throws IOException {
        super.update(value, start, update);
        FileWriter<Ticket> ticket = new FileWriter<>(Ticket.generator);
        ticket.update(value , start + 80 ,update);
    }
}
