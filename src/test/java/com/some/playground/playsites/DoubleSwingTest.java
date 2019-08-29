package com.some.playground.playsites;

import com.some.playground.visiting.Kid;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class DoubleSwingTest {

    private DoubleSwing doubleSwing;
    private Kid anxiousKid;
    private Kid waitingKid;

    @Before
    public void before() {
        doubleSwing = new DoubleSwing("456789");
        anxiousKid = new Kid("Name Surname", 10, "asdjkl", false);
        waitingKid = new Kid("Name Surname2", 10, "123456", true);
    }

    @Test
    public void getUtilisation() {
        assertEquals(0, doubleSwing.getUtilisation(), 0);
    }

    @Test
    public void getUtilisation2() {
        doubleSwing.addKid(anxiousKid, false);
        assertEquals(0, doubleSwing.getUtilisation(), 0);
    }

    @Test
    public void getUtilisation3() {
        doubleSwing.addKid(anxiousKid, false);
        doubleSwing.addKid(waitingKid, false);
        assertEquals(100, doubleSwing.getUtilisation(), 0);
    }
}