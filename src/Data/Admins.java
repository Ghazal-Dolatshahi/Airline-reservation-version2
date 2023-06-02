package Data;

import java.io.IOException;

public class Admins {
  private final FileWriter<Admin> adminsFile = new FileWriter<>(Admin.generator);

    public Admins() throws IOException {
        if (adminsFile.getFile().length() == 0) {
            defaultAdmin();
        }
    }
    public long searchIndex(int start , String value) {
        try {
            return adminsFile.searchIndex(start,value);
        }catch (Exception e){
            return 0;
        }
    }
    public Admin search(Admin admin) {
        try {
            return adminsFile.search(0, admin.username, admin.password).get(0);
        } catch (Exception exception) {
            return null;
        }
    }

    public void defaultAdmin() throws IOException {
        Admin admin = new Admin("admin", "2004", "0");
        adminsFile.write(admin);
    }
}

