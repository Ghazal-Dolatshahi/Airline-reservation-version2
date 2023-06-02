package Data;

import java.io.IOException;
import java.util.ArrayList;
public class Flights {
    private final FileWriter<Flight> flightsFile = new FileWriter<>(Flight.generator);

    public Flights() throws IOException {
        if(flightsFile.getFile().length() == 0) {
            defaultFlights();
        }
    }

    public FileWriter<Flight> getFlightsFile() {
        return flightsFile;
    }

    public ArrayList<Flight> search(int start , String...values) {
        try {
            return flightsFile.search(start,values);
        }catch (Exception e){
            return null;
        }
    }
    public long searchIndex(int start , String value) {
        try {
            return flightsFile.searchIndex(start,value);
        }catch (Exception e){
            return 0;
        }
    }

    public void write(Flight flight) throws IOException {
        flightsFile.write(flight);
    }
    public boolean remove(String flightId) throws IOException {
       return flightsFile.remove(flightId , 0);
    }
    public void update(String value , String update , int start) throws IOException {
        flightsFile.update(value , start , update);
    }
    public void defaultFlights() throws IOException {

        Flight f = new Flight("wx-20", "Yazd", "Shiraz", "1402/09/25", "12:30", "2500000", "110");
        Flight f1 = new Flight("gh-45", "Kish", "Tehran", "1402/01/31", "08:45", "2250000", "105");
        Flight f2 = new Flight("wx-67", "Tehran", "Mashhad", "1402/08/30", "04:25", "1500000", "102");
        Flight f3 = new Flight("ab-26", "Gorgan", "Mashhad", "1402/09/16", "01:50", "1250000", "150");
        Flight f4 = new Flight("wx-24", "Gorgan", "Isfahan", "1402/09/05", "18:55", "700000", "0");
        Flight f5 = new Flight("gh-97", "Kish", "Tehran", "1402/10/08", "14:50", "2250000", "105");
        Flight f6 = new Flight("wx-57", "Tabriz", "Mashhad", "1402/11/22", "16:00", "2250000", "51");
        Flight f7 = new Flight("cd-44", "Mashhad", "Kish", "1402/10/18", "00:00", "2150000", "107");
        Flight f8 = new Flight("wx-18", "Yazd", "Mashhad", "1402/09/15", "14:50", "950000", "56");
        Flight f9 = new Flight("gh-22", "Yazd", "Mashhad", "1402/09/25", "20:20", "2250000", "40");
        Flight f10 = new Flight("wx-45", "Yazd", "Mashhad", "1402/06/31", "01:50", "950000", "56");
        flightsFile.write(f);
        flightsFile.write(f1);
        flightsFile.write(f2);
        flightsFile.write(f3);
        flightsFile.write(f4);
        flightsFile.write(f5);
        flightsFile.write(f6);
        flightsFile.write(f7);
        flightsFile.write(f8);
        flightsFile.write(f9);
        flightsFile.write(f10);
    }
}