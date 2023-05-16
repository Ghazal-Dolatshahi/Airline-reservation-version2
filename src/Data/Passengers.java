package Data;

import java.util.ArrayList;
import java.util.List;
public class Passengers {
    private final ArrayList<Passenger> passengersList;
    public Passengers() {
        passengersList = new ArrayList<>();
    }

    public Passenger search(Passenger passenger){
            List<Passenger> passenger1 = (passengersList.stream().filter(passengers -> passengers.similar(passenger))).toList();
        return passenger1.size() > 0 ? passenger1.get(0) : null;
    }
    public void add(Passenger passenger){passengersList.add(passenger);}
}
