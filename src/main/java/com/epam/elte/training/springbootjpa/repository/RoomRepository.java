package com.epam.elte.training.springbootjpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.elte.training.springbootjpa.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
