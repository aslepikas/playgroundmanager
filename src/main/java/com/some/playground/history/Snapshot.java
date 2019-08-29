package com.some.playground.history;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
public class Snapshot {
    private final long timeMillis;
    private final Set<UtilisationRecord> utilisationSet;
}
