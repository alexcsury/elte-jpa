package com.epam.elte.training.springbootjpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.epam.elte.training.springbootjpa.entity.Guest;
import com.epam.elte.training.springbootjpa.repository.GuestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private GuestRepository guestRepository;

    @Before
    public void setUp() {
        guestRepository.save(new Guest("Aaaa", "Bbbb"));
        guestRepository.save(new Guest("Cccc", "Dddd"));
        guestRepository.save(new Guest("Eeee", "Ffff"));
        guestRepository.save(new Guest("Eeee", "Gggg"));
    }

    @Test
    public void testCrudRepository() {
        Guest guest = new Guest("Aaaa", "Bbbb");
        guestRepository.save(guest);
        Guest searchedGuest = guestRepository.findById(guest.getGuestId()).orElseThrow(() -> new RuntimeException("Guest not found in test!"));
        assertEquals(guest.getFirstName(), searchedGuest.getFirstName());
        assertEquals(guest.getLastName(), searchedGuest.getLastName());
        assertEquals(guest.getGuestId(), searchedGuest.getGuestId());

        searchedGuest.setFirstName("Zzzz");
        guestRepository.save(searchedGuest);
        Guest updatedGuest = guestRepository.findById(searchedGuest.getGuestId()).orElseThrow(() -> new RuntimeException("Guest not found in test!"));
        assertEquals(searchedGuest.getFirstName(), updatedGuest.getFirstName());

        guestRepository.delete(updatedGuest);
        assertNull(guestRepository.findById(updatedGuest.getGuestId()).orElse(null));
    }

    @Test
    public void testFindAll() {
        List<Guest> listOfGuests = new ArrayList<>();
        guestRepository.findAll().forEach(listOfGuests::add);
        assertEquals(4, listOfGuests.size());
    }

    @Test
    public void testFindByFirstName() {
        List<Guest> listOfGuestsWithSpecifiedFirstName = guestRepository.findByFirstName("Eeee");
        assertEquals(2, listOfGuestsWithSpecifiedFirstName.size());
    }

    @Test
    public void testFindByLastName() {
        List<Guest> listOfGuestsWithSpecifiedLastName = guestRepository.findByLastName("Ffff");

        assertEquals(1, listOfGuestsWithSpecifiedLastName.size());
    }

    @After
    public void cleanUp() {
        guestRepository.deleteAll();
    }
}
