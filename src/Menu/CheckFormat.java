package Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFormat {
    /**
     * <span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">checks the string is a number or not</span>
     * @param str the string to be checked for validity
     * @return Integer
     */
    public Integer parseInteger(String str) {

        if (str == null || str.length() == 0) {
            return -1;
        }
        try {

            return Integer.parseInt(str);

        } catch (NumberFormatException e) {
            return -1;
        }
    }
    /**
     * <span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">checks that the first letter of the string is uppercase and the other letters are lowercase</span>
     * @param str the string to be checked for validity
     * @return true if the format is valid, otherwise returns false
     */
    public boolean checkLetter(String str) {
        for (int i = 0; i < str.length(); i++)
            if (!Character.isLetter(str.charAt(i))) return false;

        return true;
    }
    /**
     *<span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF"> Checks the entered time format</span>
     * @param time the time entered by the users
     * @return true if the entered format for the time is correct , otherwise return false
     */
    public boolean checkTime(String time) {

        Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
        Matcher matcher = pattern.matcher(time);

        return matcher.find();
    }
    /**
     * <span style = "font-family : Times New Roman ; font-size :12px ;color:#1E90FF">Checks the entered date format</span>
     * @param time  the date entered by the users
     * @return true if the entered format for the date is correct , otherwise return false
     */
    public boolean checkDate(String time) {
        try {

            LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            return date != null;
        } catch (Exception e) {
            return false;

        }
    }
}