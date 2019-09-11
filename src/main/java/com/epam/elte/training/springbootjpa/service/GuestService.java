package com.epam.elte.training.springbootjpa.service;

import com.epam.elte.training.springbootjpa.entity.Guest;
import com.epam.elte.training.springbootjpa.entity.Room;
import com.epam.elte.training.springbootjpa.repository.GuestRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private HotelService hotelService;

    @Transactional
    public void createNewGuest(String firstName, String lastName, long roomNumber) {
        Guest guest = new Guest();
        guest.setFirstName(firstName);
        guest.setLastName(lastName);
        Room room = getRoomInCurrentHotel(roomNumber);
        guest.setRoom(room);
        room.getGuestList().add(guest);
        guestRepository.save(guest);
    }

    @Transactional
    public List<Guest> findAllGuests() {
        final String hotelName = hotelService.getCurrentHotel().getHotelName();
        return guestRepository.findByHotelName(hotelName);
    }

    public List<Guest> findGuestsByRoomNumber(long roomNumber) {
        Room room = getRoomInCurrentHotel(roomNumber);
        return room.getGuestList();
    }

    private Room getRoomInCurrentHotel(long roomNumber) {
        return hotelService.getCurrentHotel()
            .getListOfRooms().stream()
            .filter(r -> r.getRoomNumber() == roomNumber)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("There is no room with that room number!"));
    }

    @Transactional
    public void deleteGuest(long guestId) {
        final Optional<Guest> guest = guestRepository.findById(guestId);
        guest.ifPresent(value -> guestRepository.delete(value));
    }
}
