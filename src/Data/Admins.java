package Data;

import java.util.ArrayList;
import java.util.List;

public class Admins {
    private final ArrayList<Admin> adminList;

    public Admins() {
        this.adminList = new ArrayList<>();
        defaultAdmin();
    }

    public Admin search(Admin admin) {

        List<Admin> admins1 = (adminList.stream()
                .filter(admins -> admins.similar(admin))).toList();
        return admins1.size() > 0 ? admins1.get(0) : null;

    }

    public void defaultAdmin() {
        Admin admin1 = new Admin("admin", "2004", "0");
        adminList.add(admin1);
    }
}

