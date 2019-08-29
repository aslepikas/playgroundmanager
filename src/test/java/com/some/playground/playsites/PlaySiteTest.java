package com.some.playground.playsites;

import com.some.playground.visiting.Kid;
import com.some.playground.visiting.Ticket;
import com.some.playground.visiting.TicketRegistry;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlaySiteTest {

    private PlaySite playSite;
    private Kid anxiousKid;
    private Kid waitingKid;
    private Kid skippingKid;
    private Ticket ticket1;
    private Ticket ticket2;
    private Ticket ticket3;


    @Before
    public void before() {
        playSite = new BallPit(1, "testId");
        anxiousKid = new Kid("Name Surname", 10, "asdjkl", false);
        waitingKid = new Kid("Name Surname2", 10, "123456", true);
        skippingKid = new Kid("Name Surname3", 10, "789456", true);
        ticket1 = new Ticket("asdjkl", 0);
        ticket2 = new Ticket("123456", 0);
        ticket3 = new Ticket("789456", 1);
        TicketRegistry ticketRegistry = TicketRegistry.getInstance();
        ticketRegistry.registerTicket(ticket1);
        ticketRegistry.registerTicket(ticket2);
        ticketRegistry.registerTicket(ticket3);
    }

    @Test
    public void removeKid() {
        playSite.addKid(anxiousKid, false);
        playSite.addKid(waitingKid, false);
        assertTrue(playSite.removeKid(waitingKid));
        assertEquals(0, playSite.getWaitingKidsCount());
    }

    @Test
    public void addKid() {
        assertTrue(playSite.addKid(anxiousKid, false));
        assertTrue(playSite.addKid(waitingKid, false));
        assertEquals(1, playSite.getPlayingKidsCount());
    }

    @Test
    public void addKid2() {
        assertTrue(playSite.addKid(waitingKid, false));
        assertFalse(playSite.addKid(anxiousKid, false));
        assertEquals(1, playSite.getPlayingKidsCount());
    }

    @Test
    public void addKid3() {
        playSite.addKid(anxiousKid, false);
        playSite.addKid(waitingKid, false);
        playSite.addKid(skippingKid, false);
        assertEquals(1, playSite.getPlayingKidsCount());

        List<Kid> waitingList = playSite.getWaitingKids();
        assertEquals(waitingList.get(0), waitingKid);
        assertEquals(waitingList.get(1), skippingKid);
    }

    @Test
    public void addKid4() {
        playSite.addKid(anxiousKid, false);
        playSite.addKid(waitingKid, false);
        playSite.addKid(skippingKid, true);
        assertEquals(1, playSite.getPlayingKidsCount());

        List<Kid> waitingList = playSite.getWaitingKids();
        assertEquals(waitingList.get(0), skippingKid);
    }

    @Test
    public void getUtilisation() throws Exception {
        assertEquals(0, playSite.getUtilisation(), 0);
    }

    @Test
    public void getUtilisation2() throws Exception {
        playSite.addKid(anxiousKid, false);
        assertEquals(100, playSite.getUtilisation(), 0);
    }

    @Test
    public void getPlayingKidsCount() {
        assertEquals(0, playSite.getPlayingKidsCount());
    }

    @Test
    public void getPlayingKidsCount2() {
        playSite.addKid(anxiousKid, false);
        assertEquals(1, playSite.getPlayingKidsCount());
    }
}