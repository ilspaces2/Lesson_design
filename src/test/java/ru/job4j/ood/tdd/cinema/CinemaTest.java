package ru.job4j.ood.tdd.cinema;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.tdd.cinima.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Disabled
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertEquals(ticket, new Ticket3D());
    }

    @Disabled
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertEquals(sessions, List.of(new Session3D()));

    }

    @Disabled
    @Test
    public void whenAdd() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertEquals(sessions, List.of(new Session3D()));
    }

    @Disabled
    @Test
    public void whenSessionsIsNull() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertNull(sessions);
    }


    @Disabled
    @Test()
    public void whenIncorrectDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            Account account = new AccountCinema();
            Cinema cinema = new Cinema3D();
            Calendar date = Calendar.getInstance();
            date.set(2070, 10, 10, 23, 00);
            Ticket ticket = cinema.buy(account, 1, 1, date);
        });
    }

    @Disabled
    @Test()
    public void whenIncorrectSeat() {
        assertThrows(IllegalArgumentException.class, () -> {
            Account account = new AccountCinema();
            Cinema cinema = new Cinema3D();
            Calendar date = Calendar.getInstance();
            date.set(2020, 10, 10, 23, 00);
            Ticket ticket = cinema.buy(account, 2000, 2000, date);
        });
    }

    @Disabled
    @Test()
    public void whenSeatIsBusy() {
        assertThrows(IllegalArgumentException.class, () -> {
            Cinema cinema = new Cinema3D();
            Calendar date = Calendar.getInstance();
            date.set(2020, 10, 10, 23, 00);
            Account account1 = new AccountCinema();
            Ticket ticket1 = cinema.buy(account1, 5, 5, date);
            Account account2 = new AccountCinema();
            Ticket ticket2 = cinema.buy(account2, 5, 5, date);
        });
    }
}