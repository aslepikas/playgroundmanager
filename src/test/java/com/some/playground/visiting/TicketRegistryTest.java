package com.some.playground.visiting;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class TicketRegistryTest {

    @Test
    public void getInstance() {
        assertNotNull(TicketRegistry.getInstance());
    }

    @Test
    public void registerTicket() {
        TicketRegistry registry = TicketRegistry.getInstance();

        Ticket ticket = new Ticket("123456", 0);
        registry.registerTicket(ticket);

        assertSame(registry.getTicket("123456"), ticket);
    }

    @Test
    public void getTicket() {
    }
}