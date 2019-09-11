package com.epam.elte.training.springbootjpa.io;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManagementConsolePrinter extends ConsolePrinter{
    @Autowired
    private HotelConsolePrinter hotelConsolePrinter;

    @Autowired
    private RoomConsolePrinter roomConsolePrinter;

    @Autowired
    private GuestConsolePrinter guestConsolePrinter;

    public void showOptions() {
        String choice = "";
        do {
            hotelConsolePrinter.printCurrentHotel();
            System.out.println("1. Hotel options");
            System.out.println("2. Room options");
            System.out.println("3. Guest options");
            System.out.println("Q. Exit the application");
            choice = scanner.nextLine();
            processChoice(choice);
        } while (shouldContinue(choice, "Q"));
    }

    private void processChoice(String option) {
        switch (option) {
            case "1":
                hotelConsolePrinter.showOptions();
                break;
            case "2":
                roomConsolePrinter.showOptions();
                break;
            case "3":
                guestConsolePrinter.showOptions();
        }
    }
}
