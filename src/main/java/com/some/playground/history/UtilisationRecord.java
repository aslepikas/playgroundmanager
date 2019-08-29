package com.some.playground.history;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UtilisationRecord {

    private final String playSiteId;

    private final float utilisation;

}
