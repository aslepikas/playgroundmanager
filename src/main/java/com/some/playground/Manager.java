package com.some.playground;

import com.some.playground.history.SnapshotManager;
import com.some.playground.playsites.PlaySite;

import java.util.concurrent.ConcurrentHashMap;

public class Manager {

    private ConcurrentHashMap<String, PlaySite> playSiteMap;

    public Manager() {
        this.playSiteMap = new ConcurrentHashMap<>();
    }

    public void registerSite(PlaySite playSite) {
        playSiteMap.put(playSite.getId(), playSite);
    }

    public PlaySite getSite(String id) {
        return playSiteMap.get(id);
    }

    public SnapshotManager startTakingSnapshotsWithInterval(long snapshotInterval) {
        SnapshotManager snapshotManager = new SnapshotManager(playSiteMap, snapshotInterval);
        snapshotManager.start();
        return snapshotManager;
    }

}
