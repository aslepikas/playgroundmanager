package com.some.playground.history;

import com.some.playground.playsites.PlaySite;
import com.some.playground.util.TimeUtil;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SnapshotManager {

    private final List<Snapshot> snapshotHistory = new LinkedList<>();
    private Map<String, PlaySite> playSiteMap;
    private ScheduledThreadPoolExecutor scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    private ScheduledFuture scheduledTask;
    @Getter
    private long snaphotIntervalMillis;
    @Getter
    private boolean recording = false;

    public SnapshotManager(Map<String, PlaySite> playSiteMap, long snaphotIntervalMillis) {
        this.playSiteMap = playSiteMap;
        this.snaphotIntervalMillis = snaphotIntervalMillis;
    }

    private void takeSnapshot() {
        if (!TimeUtil.isWorkingHours()) {
            return;
        }

        snapshotHistory.add(new Snapshot(System.currentTimeMillis(),
                playSiteMap.entrySet().stream()
                        .map(entry -> new UtilisationRecord(entry.getKey(), entry.getValue().getUtilisation()))
                        .collect(Collectors.toSet())));
    }

    public void start() {
        scheduledTask = scheduledExecutorService
                .scheduleAtFixedRate(this::takeSnapshot, 0, snaphotIntervalMillis, TimeUnit.MILLISECONDS);
        this.recording = true;
    }

    public void alterSchedule(long newSnapshotIntervalMillis) {
        this.snaphotIntervalMillis = newSnapshotIntervalMillis;
        long delay = scheduledTask.getDelay(TimeUnit.MILLISECONDS);
        scheduledTask.cancel(false);
        scheduledExecutorService
                .scheduleAtFixedRate(this::takeSnapshot, delay, snaphotIntervalMillis, TimeUnit.MILLISECONDS);
    }

    public List<Snapshot> getSnapshotHistory(long startTimeMillis, long endTimeMillis) {
        return snapshotHistory.stream().filter(snapshot -> snapshot.getTimeMillis() >= startTimeMillis &&
                snapshot.getTimeMillis() <= endTimeMillis).collect(Collectors.toList());
    }
}
