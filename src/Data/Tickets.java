package Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
public class Tickets {
    private final ArrayList<Ticket> ticketData;
    public Tickets()
    {
        this.ticketData = new ArrayList<>();
    }

    public ArrayList<Ticket> search(Ticket newTicket) {
        return (ArrayList<Ticket>) ticketData.stream().filter(ticket -> ticket.similar(newTicket)).collect(Collectors.toList());
    }

    public void add(Ticket ticket) {
       ticketData.add(ticket);

    }
    public boolean remove(Ticket ticket)
    {
       return ticketData.removeIf(t -> t.similar(ticket));
    }


    public String toString() {

        return returnString(this.ticketData);
    }
    public static String returnString(ArrayList<Ticket> tickets) {
        return (Arrays.stream(Ticket.class.getDeclaredFields()).map(data -> data.getName() + "\t\t").collect(Collectors.joining())
                + "\n" + "-".repeat(109) +
                tickets.stream().map(t -> "\n" + t).collect(Collectors.joining()));

    }
}