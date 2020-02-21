package com.lightout.domain;

import lombok.Getter;

import java.util.Arrays;

public class Board {
    @Getter
    private final int depth;

    private final int[][] initialState;
    private int[][] currentState;

    public Board(int depth, int[][] initialState) {
        this.depth = depth;
        this.initialState = initialState;
        refreshCurrentState();
    }

    public void refreshCurrentState() {
        if (currentState == null) {
            currentState = new int[getHeight()][getWidth()];
        }
        copy2DArray(initialState, currentState);
    }

    private void copy2DArray(int[][] src, int[][] dest) {
        for(int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
        }
    }

    public void apply(Piece piece, Point point) {
        for (int i = 0; i < piece.getHeight(); i++) {
            for (int j = 0; j < piece.getWidth(); j++) {
                currentState[i + point.getY()][j + point.getX()] += piece.getElement(j, i);
                currentState[i + point.getY()][j + point.getX()] %= depth;
            }
        }
    }

    public boolean isEqualState(int[][] state) {
        for (int i = 0; i < getHeight(); i++) {
            if (!Arrays.equals(currentState[i], state[i])) {
                return false;
            }
        }
        return true;
    }

    public int getHeight() {
        return initialState.length;
    }

    public int getWidth() {
        return initialState[0].length;
    }
}
