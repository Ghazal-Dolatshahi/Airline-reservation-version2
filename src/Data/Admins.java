package Data;

import java.io.IOException;

public class Admins {
  private final FileWriter<Admin> adminsFile = new FileWriter<>(Admin.generator);

    public Admins() throws IOException {
        if (adminsFile.getFile().length() == 0) {
            defaultAdmin();
        }
    }
    public Admin search(Admin admin) {
        try {
            return adminsFile.search(0, admin.username).get(0);
        } catch (Exception exception) {
            return null;
        }
    }

    public void defaultAdmin() throws IOException {

        Admin admin = new Admin("admin", "2004", "0");
        adminsFile.write(admin);
    }
}

