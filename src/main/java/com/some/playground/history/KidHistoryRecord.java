package com.some.playground.history;

import com.some.playground.playsites.PlaySite;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class KidHistoryRecord {

    private final PlaySite playSite;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

}
