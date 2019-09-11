package com.epam.elte.training.springbootjpa.io;

import com.epam.elte.training.springbootjpa.entity.Guest;
import com.epam.elte.training.springbootjpa.service.GuestService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuestConsolePrinter extends ConsolePrinter{
    @Autowired
    private GuestService guestService;

    public void showOptions() {
        String choice = "";
        do {
            System.out.println("1. Check in quest");
            System.out.println("2. Check out guest");
            System.out.println("3. Get current guests");
            System.out.println("4. Get guest by room number");
            System.out.println("B. Go back");
            choice = scanner.nextLine();
            processChoice(choice);
        } while(shouldContinue(choice, "B"));
    }

    private void processChoice(String choice) {
        switch (choice) {
            case "1":
                checkIn();
                break;
            case "2":
                checkOut();
                break;
            case "3":
                listGuests();
                break;
            case "4":
                listGuestsByRoom();
        }
    }

    private void listGuestsByRoom() {
        System.out.println("Room number:");
        String roomNumberLine = scanner.nextLine();
        long roomNumber = Long.parseLong(roomNumberLine);
        guestService.findGuestsByRoomNumber(roomNumber).forEach(System.out::println);
    }

    private void listGuests() {
        guestService.findAllGuests().forEach(System.out::println);
    }

    private void checkOut() {
        System.out.println("Room number:");
        String roomNumberLine = scanner.nextLine();
        long roomNumber = Long.parseLong(roomNumberLine);
        final List<Guest> guestsByRoomNumber = guestService.findGuestsByRoomNumber(roomNumber);
        guestsByRoomNumber.forEach(System.out::println);
        String guestIdLine = scanner.nextLine();
        long guestId = Long.parseLong(guestIdLine);
        if (guestsByRoomNumber.stream().anyMatch(guest -> guest.getGuestId() == guestId)) {
            guestService.deleteGuest(guestId);
        }
    }

    private void checkIn() {
        System.out.println("First name of Guest:");
        String firstName = scanner.nextLine();
        System.out.println("Last name of Guest:");
        String lastName = scanner.nextLine();
        System.out.println("Room number for checking in:");
        String roomNumberLine = scanner.nextLine();
        long roomNumber = Long.parseLong(roomNumberLine);
        guestService.createNewGuest(firstName, lastName, roomNumber);
    }

}
