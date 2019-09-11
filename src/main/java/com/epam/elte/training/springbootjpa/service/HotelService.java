package com.epam.elte.training.springbootjpa.service;

import com.epam.elte.training.springbootjpa.entity.Hotel;
import com.epam.elte.training.springbootjpa.repository.HotelRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    private Hotel currentHotel;

    @Transactional
    public void createNewHotel(String hotelName) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelName);
        hotelRepository.save(hotel);
    }

    @Transactional
    public Iterable<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Transactional
    public Hotel getCurrentHotel() {
        if(currentHotel != null) {
            return hotelRepository.findById(currentHotel.getId()).get();
        }
        return null;
    }

    @Transactional
    public void setCurrentHotel(long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        hotel.ifPresent(value -> currentHotel = value);
    }

    @Transactional
    public void deleteHotel(long id) {
        hotelRepository.deleteById(id);
    }
}
