package Data;

import java.io.FileNotFoundException;
import java.io.IOException;
public class Passengers {
   private final FileWriter<Passenger> passengersFile = new FileWriter<>(Passenger.generator);

    public Passengers() throws FileNotFoundException {
    }

    public FileWriter<Passenger> getPassengersFile() {
        return passengersFile;
    }

    public void write(Passenger passenger) throws IOException {
        passengersFile.write(passenger);
    }
    public Passenger search(Passenger passenger) {
        try {
            return passengersFile.search(0, passenger.username, passenger.password).get(0);
        }catch (Exception e){
            return null;
        }
    }
    public long searchIndex(int start , String value) {
        try {
            return passengersFile.searchIndex(start,value);
        }catch (Exception e){
            return 0;
        }
    }
    public boolean update(String value , String update , int start) throws IOException {
      return passengersFile.update(value , start , update);
    }
}
