package com.some.playground.visiting;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TicketRegistry {

    private static TicketRegistry ticketRegistry = new TicketRegistry();
    private Map<String, Ticket> ticketMap = new ConcurrentHashMap<>();

    private TicketRegistry() {
    }

    public static TicketRegistry getInstance() {
        return ticketRegistry;
    }

    public void registerTicket(Ticket ticket) {
        ticketMap.put(ticket.getId(), ticket);
    }

    public Ticket getTicket(String id) {
        return ticketMap.get(id);
    }

}
