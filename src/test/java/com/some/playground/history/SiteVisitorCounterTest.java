package com.some.playground.history;

import com.some.playground.playsites.Carousel;
import com.some.playground.playsites.DoubleSwing;
import com.some.playground.playsites.PlaySite;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SiteVisitorCounterTest {

    private SiteVisitorCounter visitorCounter;

    private PlaySite playSite1 = new Carousel(4, "456");
    private PlaySite playSite2 = new DoubleSwing("789");

    @Before
    public void before() {
        visitorCounter = SiteVisitorCounter.getInstance();
    }

    @Test
    public void getInstance() {
        assertSame(SiteVisitorCounter.getInstance(), SiteVisitorCounter.getInstance());
    }

    @Test
    public void registerVisitor() {
        visitorCounter.registerVisitor(playSite1);
        assertEquals(visitorCounter.visitorCount(LocalDate.now(), playSite1), 1);
    }

    @Test
    public void registerVisitor2() {
        visitorCounter.registerVisitor(playSite2);
        visitorCounter.registerVisitor(playSite2);
        assertEquals(visitorCounter.visitorCount(LocalDate.now(), playSite2), 2);
    }

    @Test
    public void registerVisitor3() {
        visitorCounter.registerVisitor(playSite2);
        visitorCounter.registerVisitor(playSite2);
        assertEquals(visitorCounter.visitorCount(LocalDate.of(2018, 8, 1), playSite2), 0);
    }

}