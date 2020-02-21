package com.lightout.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Point {
    @Getter
    private int x;

    @Getter
    private int y;

    @Override
    public String toString() {
        return x + "," + y;
    }
}
