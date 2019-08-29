package com.some.playground.playsites;

import com.some.playground.history.HistoryManager;
import com.some.playground.history.KidHistoryRecord;
import com.some.playground.history.SiteVisitorCounter;
import com.some.playground.visiting.Kid;
import com.some.playground.visiting.TicketRegistry;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class PlaySite {

    @Getter
    private final int slots;
    @Getter
    private final String id;
    private int nextVipPosition = 0;

    private ArrayList<Kid> waitingQueue;
    private ConcurrentHashMap<Kid, Long> playingKids;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public PlaySite(int slots, String id) {
        this.slots = slots;
        this.id = id;
        this.waitingQueue = new ArrayList<>();
        this.playingKids = new ConcurrentHashMap<>();
    }

    public boolean removeKid(Kid kid) {
        try {
            readWriteLock.writeLock().lock();
            if (!waitingQueue.remove(kid)) {
                Long playStartTime = playingKids.remove(kid);
                if (null != playStartTime) {
                    registerHistory(kid, playStartTime);
                    letOtherKidPlay();
                    return true;
                }
                return false;
            }
            return true;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private void registerHistory(Kid kid, Long playStartTime) {
        HistoryManager.getInstance().addHistoryRecord(kid,
                new KidHistoryRecord(playStartTime, System.currentTimeMillis(), this));
    }

    private void letOtherKidPlay() {
        if (waitingQueue.size() > 0) {
            letKidPlay(waitingQueue.get(0));
            waitingQueue.remove(0);
        }
    }

    public boolean addKid(Kid kid, boolean wishesToSkipLine) {
        try {
            readWriteLock.writeLock().lock();
            if (playingKids.size() < slots) {
                letKidPlay(kid);
                return true;
            } else if (kid.isWaiting()) {
                enqueueKid(kid, wishesToSkipLine);
                return true;
            }
            return false;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private void letKidPlay(Kid kid) {
        playingKids.put(kid, System.currentTimeMillis());
        SiteVisitorCounter.getInstance().registerVisitor(this);
    }

    private void enqueueKid(Kid kid, boolean wishesToSkipLine) {
        if (wishesToSkipLine && TicketRegistry.getInstance().getTicket(kid.getTicketNumber()).skipLine()) {
            waitingQueue.add(nextVipPosition, kid);
            nextVipPosition = Math.min(nextVipPosition + 4, waitingQueue.size());
        } else {
            waitingQueue.add(kid);
        }
    }

    public float getUtilisation() {
        return (100 * playingKids.size()) / (float) slots;
    }

    public int getPlayingKidsCount() {
        return playingKids.size();
    }

    public List<Kid> getWaitingKids() {
        return Collections.unmodifiableList(waitingQueue);
    }

    public int getWaitingKidsCount() {
        return waitingQueue.size();
    }
}
