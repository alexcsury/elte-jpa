package com.epam.elte.training.springbootjpa.service;

import com.epam.elte.training.springbootjpa.entity.DoubleRoom;
import com.epam.elte.training.springbootjpa.entity.KingRoom;
import com.epam.elte.training.springbootjpa.entity.Room;
import com.epam.elte.training.springbootjpa.entity.SingleRoom;
import com.epam.elte.training.springbootjpa.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomService {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public void createNewRoom(long roomNumber, String roomTpye) {
        Room room = createRoomByType(roomTpye);
        room.setRoomNumber(roomNumber);
        room.setHotel(hotelService.getCurrentHotel());
        hotelService.getCurrentHotel().getListOfRooms().add(room);
        roomRepository.save(room);
    }

    private Room createRoomByType(String roomTpye) {
        String room = roomTpye.toLowerCase();
        switch (room) {
            case "single":
                return new SingleRoom();
            case "double":
                return new DoubleRoom();
            case "king":
                return new KingRoom();
            default:
                throw new IllegalArgumentException("No such room exists with type " + roomTpye);
        }
    }

    public boolean noCurrentHotel() {
        return hotelService.getCurrentHotel() == null;
    }

    @Transactional
    public Room getRoomInCurrentHotel(long roomNumber) {
        Room room = roomRepository.findByRoomNumber(roomNumber).orElse(null);
        if(room != null && room.getHotel().equals(hotelService.getCurrentHotel())) {
            return room;
        }
        throw new IllegalArgumentException("No Room " + roomNumber + " in the current hotel!");
    }
}
