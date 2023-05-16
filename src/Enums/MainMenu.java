package Enums;

import java.util.Arrays;

public enum MainMenu {

    SIGN_IN,
    SIGN_UP,
    EXIT;
    public static void printMenu(){
        Arrays.stream(values()).forEach(option -> System.out.println((option.ordinal() + 1) + "- " +( option.name().replace("_" , " ")).toLowerCase()));
    }
    public static MainMenu value(int command){
        try {
            return values()[command - 1];
        }catch (Exception e){
            return  null;
        }
    }
}
