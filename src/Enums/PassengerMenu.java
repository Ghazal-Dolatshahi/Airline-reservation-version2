package Enums;

import java.util.Arrays;

public enum PassengerMenu {
    SIGN_OUT,
    CHANGE_PASSWORD,
    SEARCH_FLIGHT_TICKETS,
    BOOKING_TICKET,
    TICKET_CANCELLATION,
    BOOKED_TICKET,
    ADD_CHARGE;
    public static void printMenu(){
        Arrays.stream(values()).forEach(option -> System.out.println((option.ordinal()) + "- " +( option.name().replace("_" , " ")).toLowerCase()));
    }
    public static PassengerMenu value(int command){
        try {
            return values()[command];
        }catch (Exception e){
            return  null;
        }
    }
}
