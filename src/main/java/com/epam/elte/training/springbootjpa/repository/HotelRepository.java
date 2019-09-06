package com.epam.elte.training.springbootjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.elte.training.springbootjpa.entity.Hotel;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, String> {
}
