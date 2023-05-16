package Enums;

import java.util.Arrays;

public enum AdminMenu {
    SIGN_OUT,
    ADD,
    UPDATE,
    REMOVE,
    FLIGHT_SCHEDULES;
    public static void printMenu(){
        Arrays.stream(values()).forEach(option -> System.out.println((option.ordinal()) + "- " +( option.name().replace("_" , " ")).toLowerCase()));
    }
    public static AdminMenu value(int command){
        try {
            return values()[command];
        }catch (Exception e){
            return  null;
        }
    }
}
