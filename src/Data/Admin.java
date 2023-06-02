package Data;

public class Admin extends Passenger {

    public Admin(String username, String password, String charge) {
        super(username, password, charge);
    }

    static Generator<Admin> generator = new Generator<>() {
        @Override
        public Admin generateToRead(String... values) {
            return new Admin(
                    values[0],
                    values[1],
                    values[2]
            );
        }

        @Override
        public String generateToWrite(Admin element) {
            String str = "";
            StringBuilder strBuilder = new StringBuilder(str);
            strBuilder.append(FileWriter.fixStringToWrite(element.getUsername()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getPassword()));
            strBuilder.append(FileWriter.fixStringToWrite(element.getCharge()));

            return strBuilder.substring(0);
        }

        @Override
        public String fileAddress() {
            return "Admin file.dat";
        }

        @Override
        public int recordSize() {
            return 120;
        }
    };
}
