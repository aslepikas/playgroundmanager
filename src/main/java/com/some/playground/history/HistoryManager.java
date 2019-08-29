package com.some.playground.history;

import com.some.playground.visiting.Kid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class HistoryManager {

    private static HistoryManager instance = new HistoryManager();
    private ConcurrentHashMap<Kid, List<KidHistoryRecord>> kidHistoryMap = new ConcurrentHashMap<>();

    private HistoryManager() {
    }

    public static HistoryManager getInstance() {
        return instance;
    }

    public synchronized void addHistoryRecord(Kid kid, KidHistoryRecord record) {
        List<KidHistoryRecord> recordList = kidHistoryMap.get(kid);
        if (recordList != null) {
            recordList.add(record);
        } else {
            recordList = new ArrayList<>();
            recordList.add(record);
            kidHistoryMap.put(kid, recordList);
        }
    }

    private List<KidHistoryRecord> getKidPlayHistory(Kid kid) {
        return Collections.unmodifiableList(kidHistoryMap.get(kid));
    }

}
