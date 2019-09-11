package com.epam.elte.training.springbootjpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.elte.training.springbootjpa.entity.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
    List<Guest> findByFirstName(String firstName);

    List<Guest> findByLastName(String lastName);

    @Query("SELECT g FROM Guest g INNER JOIN g.room r WHERE r.hotel.hotelName = :hotelName")
    List<Guest> findByHotelName(String hotelName);
}
