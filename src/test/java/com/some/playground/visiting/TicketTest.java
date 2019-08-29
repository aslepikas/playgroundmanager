package com.some.playground.visiting;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TicketTest {

    private Ticket testTicket;

    @Test
    public void skipLine() {
        testTicket = new Ticket("adsf789", 5);
        assertTrue(testTicket.skipLine());
    }

    @Test
    public void skipLine1() {
        testTicket = new Ticket("adsf789", 0);
        assertFalse(testTicket.skipLine());
    }

    @Test
    public void skipLine2() {
        testTicket = new Ticket("adsf789", 1);
        testTicket.skipLine();
        assertFalse(testTicket.canSkipLine());
    }

    @Test
    public void skipLine3() {
        testTicket = new Ticket("adsf789", 2);
        testTicket.skipLine();
        assertTrue(testTicket.canSkipLine());
    }


}