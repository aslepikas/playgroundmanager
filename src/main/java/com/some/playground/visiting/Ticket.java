package com.some.playground.visiting;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Ticket {

    private final String id;

    private int skipCount;

    public boolean skipLine() {
        if (skipCount > 0) {
            skipCount--;
            return true;
        }
        return false;
    }

    public boolean canSkipLine() {
        return skipCount > 0;
    }
}
