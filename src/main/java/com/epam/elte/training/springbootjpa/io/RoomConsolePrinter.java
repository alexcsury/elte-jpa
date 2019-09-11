package com.epam.elte.training.springbootjpa.io;

import com.epam.elte.training.springbootjpa.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoomConsolePrinter extends ConsolePrinter{
    @Autowired
    private RoomService roomService;

    public void showOptions() {
        if (roomService.noCurrentHotel()) {
            return;
        }
        String choice = "";
        do {
            System.out.println("1. Create room");
            System.out.println("B. Go back");
            choice = scanner.nextLine();
            processChoice(choice);
        } while (shouldContinue(choice, "B"));
    }

    private void processChoice(String choice) {
        if (choice.equals("1")) {
            createRoom();
        }
    }

    private void createRoom() {
        System.out.println("Room number:");
        String roomNumberLine = scanner.nextLine();
        long roomNumber = Long.parseLong(roomNumberLine);
        System.out.println("Room type (SINGLE, DOUBLE, KING):");
        String roomType = scanner.nextLine();
        roomService.createNewRoom(roomNumber, roomType);
    }
}
