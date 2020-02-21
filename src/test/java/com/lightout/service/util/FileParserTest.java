package com.lightout.service.util;

import com.lightout.domain.Board;
import com.lightout.domain.Piece;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FileParserTest {

    @Test
    public void testParseBoardWhenCorrectData() {
        Board board = FileParser.parseBoard(2, "001,011,011");
        assertEquals(2, board.getDepth());
        assertTrue(board.isEqualState(new int[][] {
                {0, 0, 1},
                {0, 1, 1},
                {0, 1, 1}
        }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseBoardWhenIllegalDepth() {
        FileParser.parseBoard(5, "001,011,011");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseBoardWhenBoardIsNotRectangular() {
        FileParser.parseBoard(2, "001,01,011");
    }

    @Test
    public void testParsePiecesWithCorrectData() {
        List<Piece> pieces = FileParser.parsePieces(3, 3, ".X,XX");
        assertEquals(1, pieces.size());
        assertTrue(pieces.get(0).isEqualStructure(new int[][] {
                {0, 1},
                {1, 1}
        }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsePiecesWhenPieceIsNotRectangular() {
        FileParser.parsePieces(3, 3, "X,XX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsePiecesWhenPieceIsGreaterThanBoard() {
        FileParser.parsePieces(3, 3, ".XXX,XXXX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParsePiecesWhenPieceHasInvalidSymbol() {
        FileParser.parsePieces(3, 3, ".XHX,XXXX");
    }
}
