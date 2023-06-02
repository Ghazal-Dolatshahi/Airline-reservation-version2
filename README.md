## Airlines reservation system "Version 2"

  This project aims to simulate the online reservation system of airline tickets in a console application.  
  The airline reservations System contains airline schedules, passenger reservations, and ticket records.  
  This system includes two types of users :

   1. passengers

   2. admin

   ### Options:

  + All the proccess are performed on the "Random access file".

 + The admin user is pre-defined with a fixed username and password.
    
    + The admin is able to add, remove and update flight details.
    + The admin can see the flight schedules.
    + The admin cen be registered as a passenger.


 + First , the passenger should create an account (sign up) and then sign in with her/his password and username.
    +  The passenger is able to change password, booking ticket, search flights, cancel the tickets,
        add charge and see the booked tickets.
     + Filter tickets based on flight id, origin, destination, date, time, price range in “Search flights” mode.
     + The “Booking ticket” mode is based on flight id. By booking each ticket, a unique code is generated as a ticket id.
     + The “ticket cancellation” process by each ticket id would be confirmed and the refund would be credited back to the 
       account(charge).
    + The “Booked tickets” mode includes all reserved tickets of the user.
    + Passengers have a charging section in their profile where they enter the desired amount. (“Add charge”).

     ### Note:

     ~ The number of seats in each flight decreases by booking a ticket and increases by canceling it.

    ~ Every time a ticket is booked, the ticket amount is deducted from the passenger's charge.


