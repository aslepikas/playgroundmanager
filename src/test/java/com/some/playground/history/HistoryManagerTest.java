package com.some.playground.history;

import com.some.playground.playsites.BallPit;
import com.some.playground.playsites.PlaySite;
import com.some.playground.visiting.Kid;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class HistoryManagerTest {

    private HistoryManager manager;
    private Kid anxiousKid;
    private PlaySite playsite;

    @Before
    public void before() {
        manager = HistoryManager.getInstance();
        anxiousKid = new Kid("John Doe", 10, "789", false);
        playsite = new BallPit(2, "789456");
    }

    @Test
    public void addHistoryRecord() {
        manager.addHistoryRecord(anxiousKid, new KidHistoryRecord(playsite, LocalDateTime.of(2019, 8, 4, 10, 15),
                LocalDateTime.of(2019, 8, 4, 10, 25)));

        assertEquals(manager.getKidPlayHistory(anxiousKid).size(), 1);
    }
}