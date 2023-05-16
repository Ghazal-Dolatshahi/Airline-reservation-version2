package Enums;

import java.util.Arrays;

public enum FlightField {
    FLIGHT_ID ,
    ORIGIN,
    DESTINATION,
    DATE ,
    TIME ,
    PRICE ,
    SEATS;
    public static void printMenu(){
        Arrays.stream(values()).forEach(option -> System.out.println((option.ordinal() + 1) + "- " +( option.name().replace("_" , " ")).toLowerCase()));
    }
    public static FlightField value(int command){
        try {
            return values()[command - 1];
        }catch (Exception e){
            return  null;
        }
    }
}
