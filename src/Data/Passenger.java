package Data;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Passenger {
    protected String username;
    protected String password;
    protected String charge;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Passenger(String username, String password, String charge) {
        this.username = username;
        this.password = password;
        this.charge = charge;
    }

    public boolean similar(Passenger passenger){
        return (Objects.equals(passenger.getPassword(), this.getPassword()) && Objects.equals(passenger.getUsername(), this.getUsername()));
    }
    @Override
    public String toString() {
        return Arrays.stream(this.getClass().getDeclaredFields()).map(field -> {
            try {
                return field.get(this) + "\t\t";
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining());
    }
}
