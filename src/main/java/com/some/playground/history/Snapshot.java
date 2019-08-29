package com.some.playground.history;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public class Snapshot {
    private final LocalDateTime dateTime;
    private final Set<UtilisationRecord> utilisationSet;
}
