package Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFormat {
    public Integer parseInteger(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }
        try {

            return Integer.parseInt(str);

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean checkLetter(String str) {
        for (int i = 0; i < str.length(); i++)
            if (!Character.isLetter(str.charAt(i))) return false;

        return true;
    }

    public boolean checkTime(String time) {

        Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
        Matcher matcher = pattern.matcher(time);

        return matcher.find();
    }

    public boolean checkDate(String time) {
        try {

            LocalDate date = LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            return date != null;
        } catch (Exception e) {
            return false;

        }
    }
}