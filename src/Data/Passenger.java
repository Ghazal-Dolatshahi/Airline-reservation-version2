package Data;

import java.util.Objects;

public class Passenger{
    protected String username;
    protected String password;
    protected String charge;

    public Passenger(String username, String password, String charge) {
        this.username = username;
        this.password = password;
        this.charge = charge;
    }

    public Passenger(String value) {
        this.username = value;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCharge() {
        return charge;
    }

    static Generator<Passenger> generator = new Generator<>() {
        @Override
        public Passenger generateToRead(String... values) {
            return new Passenger(
                    values[0],
                    values[1],
                    values[2]
            );
        }

        @Override
        public String generateToWrite(Passenger element) {
            String str = "";
            StringBuilder strBuilder = new StringBuilder(str);
            strBuilder.append(FileWriter.fixStringToWrite(element.getUsername()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getPassword()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getCharge()));

            return strBuilder.substring(0);
        }

        @Override
        public String fileAddress() {
            return "Passenger file.dat";
        }

        @Override
        public int recordSize() {
            return 120;
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(username, passenger.username) && Objects.equals(password, passenger.password) && Objects.equals(charge, passenger.charge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, charge);
    }
}
