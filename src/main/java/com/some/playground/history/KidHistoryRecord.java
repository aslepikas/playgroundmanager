package com.some.playground.history;

import com.some.playground.playsites.PlaySite;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KidHistoryRecord {

    private final long startTimeMillis;
    private final long endTimeMillis;
    private final PlaySite playSite;

}
