package com.some.playground.visiting;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Kid {

    private final String name;
    private final int age;
    private final String ticketNumber;
    private final boolean waiting;

}
