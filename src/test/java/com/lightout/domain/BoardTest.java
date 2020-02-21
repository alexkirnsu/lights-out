package com.lightout.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testApply() {
        Board board = new Board(2, new int[][]{
                {0, 0, 1},
                {0, 1, 1},
                {0, 1, 1}
        });
        Piece piece = new Piece(new int[][]{
                {0, 1},
                {1, 1}
        }, board.getHeight(), board.getWidth());
        board.apply(piece, new Point(1, 0));

        assertTrue(board.isEqualState(new int[][] {
                {0, 0, 0},
                {0, 0, 0},
                {0, 1, 1}
        }));
    }
}
