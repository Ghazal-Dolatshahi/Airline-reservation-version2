package Data;

import java.util.Objects;
public class Admin extends Passenger {

    public Admin(String username, String password, String charge) {
        super(username, password, charge);
    }

    public boolean similar(Admin admin){
        return (Objects.equals(admin.getPassword(), "2004") && Objects.equals(admin.getUsername(), "admin"));
    }
}
