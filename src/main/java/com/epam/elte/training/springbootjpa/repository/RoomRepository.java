package com.epam.elte.training.springbootjpa.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.elte.training.springbootjpa.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    public Optional<Room> findByRoomNumber(long roomNumber);
}
