package Menu;

import Data.*;
import java.io.IOException;

public class DataBase {

    Admins admins = new Admins();
    Passengers passengers = new Passengers();
    Flights flights = new Flights();
    Tickets tickets = new Tickets();
    public DataBase() throws IOException {
    }
}
