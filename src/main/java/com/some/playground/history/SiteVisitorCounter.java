package com.some.playground.history;

import com.some.playground.playsites.PlaySite;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class SiteVisitorCounter {

    private static SiteVisitorCounter instance = new SiteVisitorCounter();
    private Map<LocalDate, Map<PlaySite, LongAdder>> recordMap = new ConcurrentHashMap<>();

    private SiteVisitorCounter() {
    }

    public static SiteVisitorCounter getInstance() {
        return instance;
    }

    public void registerVisitor(PlaySite playSite) {
        recordMap.computeIfAbsent(LocalDate.now(), k -> new ConcurrentHashMap<>())
                .computeIfAbsent(playSite, k -> new LongAdder()).increment();
    }

    public int visitorCount(LocalDate date, PlaySite playSite) {
        return recordMap.getOrDefault(date, Collections.emptyMap()).getOrDefault(playSite, new LongAdder()).intValue();
    }

}
