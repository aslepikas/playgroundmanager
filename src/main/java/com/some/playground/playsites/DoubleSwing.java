package com.some.playground.playsites;

public class DoubleSwing extends PlaySite {

    public DoubleSwing(String id) {
        super(2, id);
    }

    @Override
    public float getUtilisation() {
        return getSlots() > getPlayingKidsCount() ? 0 : 100;
    }
}
