package com.epam.elte.training.springbootjpa.io;

import com.epam.elte.training.springbootjpa.entity.Hotel;
import com.epam.elte.training.springbootjpa.service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelConsolePrinter extends ConsolePrinter{

    @Autowired
    private HotelService hotelService;

    public void showOptions() {
        String choice = "";
        do {
            System.out.println("1. Change current Hotel");
            System.out.println("2. Create new Hotel");
            System.out.println("3. Delete a Hotel");
            System.out.println("B. Go back");
            choice = scanner.nextLine();
            processChoice(choice);
        } while(shouldContinue(choice, "B"));
    }

    private void processChoice(String choice) {
        switch (choice) {
            case "1":
                changeCurrentHotel();
                break;
            case "2":
                createNewHotel();
                break;
            case "3":
                deleteHotel();
        }
    }


    private void changeCurrentHotel() {
        hotelService.getAllHotels().forEach(hotel -> {
            System.out.println(hotel.getId() + ". " + hotel.getHotelName());
        });
        String choice = scanner.nextLine();
        long hotelId = Long.parseLong(choice);
        hotelService.setCurrentHotel(hotelId);
    }

    private void createNewHotel() {
        System.out.println("Enter new Hotel name: ");
        String newHotelName = scanner.nextLine();
        hotelService.createNewHotel(newHotelName);
    }

    private void deleteHotel() {
        hotelService.getAllHotels().forEach(hotel -> {
            System.out.println(hotel.getId() + ". " + hotel.getHotelName());
        });
        String choice = scanner.nextLine();
        long hotelId = Long.parseLong(choice);
        hotelService.deleteHotel(hotelId);
    }

    public void printCurrentHotel() {
        Hotel currentHotel = hotelService.getCurrentHotel();
        if(currentHotel != null) {
            System.out.println("Currently managing: " + currentHotel.getHotelName());
        } else {
            System.out.println("Currently not managing any hotels.");
        }
    }
}
