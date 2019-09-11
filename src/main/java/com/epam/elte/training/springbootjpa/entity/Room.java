package com.epam.elte.training.springbootjpa.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "room_type")
public abstract class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long roomNumber;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Guest> guestList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "hotelName")
    private Hotel hotel;

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public void setGuestList(List<Guest> guestList) {
        this.guestList = guestList;
    }

    public void addGuest(Guest guest) {
        guestList.add(guest);
    }

    public void removeGuest(Guest guest) {
        guestList.remove(guest);
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
