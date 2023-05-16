package Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Flights {
    private final ArrayList<Flight> flightData;

    public Flights() {
        flightData  = new ArrayList<>();
        defaultFlights();
    }

    public ArrayList<Flight> search(Flight newFlight) {
        return (ArrayList<Flight>) flightData.stream().filter(flight -> flight.similar(newFlight)).collect(Collectors.toList());

    }

    public void add(Flight flight) {
        flightData.add(flight);
    }

    public boolean remove(Flight flight) {
        return flightData.removeIf(f -> f.similar(flight));
    }
    public void update(Flight oldf, Flight newf) {
        oldf.update(newf);
    }

    @Override
    public String toString() {
        return returnString(this.flightData);
    }

    public static String returnString(ArrayList<Flight> flights) {
        return (Arrays.stream(Flight.class.getDeclaredFields()).map(data -> data.getName() + "        ").collect(Collectors.joining())
                + "\n" + "\u001b[35m-\u001b[0m".repeat(109) +
                flights.stream().map(f -> "\n" + f).collect(Collectors.joining()));

    }

    public void defaultFlights() {
        flightData.addAll(List.of(
                new Flight("wx-20", "Yazd", "Shiraz", "1402/09/25", "12:30", "2500000", "110"),
                new Flight("gh-45", "Kish", "Tehran", "1402/01/31", "08:45", "2250000", "105"),
                new Flight("wx-67", "Tehran", "Mashhad", "1402/08/30", "04:25", "1500000", "102"),
                new Flight("ab-26", "Gorgan", "Mashhad", "1402/09/16", "01:50", "1250000", "150"),
                new Flight("wx-24", "Gorgan", "Isfahan", "1402/09/05", "18:55", "700000", "0"),
                new Flight("gh-97", "Kish", "Tehran", "1402/10/08", "14:50", "2250000", "105"),
                new Flight("wx-57", "Tabriz", "Mashhad", "1402/11/22", "16:00", "2250000", "51"),
                new Flight("cd-44", "Mashhad", "Kish", "1402/10/18", "00:00", "2150000", "107"),
                new Flight("wx-18", "Yazd", "Mashhad", "1402/09/15", "14:50", "950000", "56"),
                new Flight("gh-22", "Yazd", "Mashhad", "1402/09/25", "20:20", "2250000", "40"),
                new Flight("wx-45", "Yazd", "Mashhad", "1402/06/31", "01:50", "950000", "56"),
                new Flight("wx-78", "Yazd", "Mashhad", "1402/09/15", "15:50", "3000000", "78"),
                new Flight("wx-122", "Yazd", "Mashhad", "1402/09/15", "14:50", "3000000", "45")));
    }
}