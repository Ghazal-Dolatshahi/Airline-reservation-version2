package Data;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
public class Tickets {
    FileWriter<Ticket> ticketsFile = new FileWriter<>(Ticket.generator);

    public Tickets() throws FileNotFoundException {
    }

    public ArrayList<Ticket> search(int start ,String... value) {
        try {
            return ticketsFile.search(start, value);
        }catch (Exception e){
            return null;
        }
    }

    public void write(Ticket ticket) throws IOException {
        ticketsFile.write(ticket);

    }
    public void remove(Ticket ticket) throws IOException {
        ticketsFile.remove(String.valueOf(ticket.getTicketId()), 0);
    }
    public int findMaxId() throws IOException {
        String temp;
        int temp2 = 0;

        for (int i = 0; i < ticketsFile.getFile().length(); i += ticketsFile.getGenerator().recordSize()) {
            temp = ticketsFile.fixStringToRead(i);
            if(Integer.parseInt(temp) >=  temp2){
                temp2 = Integer.parseInt(temp);
            }
        }
        return temp2 + 1;
    }
}