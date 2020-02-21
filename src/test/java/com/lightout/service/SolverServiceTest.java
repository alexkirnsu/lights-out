package com.lightout.service;

import com.lightout.domain.Board;
import com.lightout.domain.Piece;
import com.lightout.service.impl.SolverServiceImpl;
import com.lightout.service.util.FileParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SolverServiceTest {

    private SolverService service = new SolverServiceImpl();

    @Test(expected = RuntimeException.class)
    public void unsolvableProblemTest() {
        Board board = FileParser.parseBoard(2, "000,000,000");
        List<Piece> pieces = FileParser.parsePieces(3, 3, "X X X");
        service.solve(board, pieces);
    }

    @Test
    public void testSimpleProblem() {
        Board board = FileParser.parseBoard(2, "001,011,011");
        List<Piece> pieces = FileParser.parsePieces(3, 3, ".X,XX XX .X,.X,XX");
        assertEquals("0,1 0,2 1,0", service.solve(board, pieces));
    }

    @Test
    public void test01ProblemSolution() {
        Board board = FileParser.parseBoard(2, "100,101,011");
        List<Piece> pieces = FileParser.parsePieces(3, 3,
                "..X,XXX,X.. X,X,X .X,XX XX.,.X.,.XX XX,X. XX .XX,XX.");
        assertEquals("0,0 0,0 1,0 0,0 0,0 0,2 0,1", service.solve(board, pieces));
    }

    @Test
    public void test02ProblemSolution() {
        Board board = FileParser.parseBoard(2, "0100,0110,1010,1110");
        List<Piece> pieces = FileParser.parsePieces(4, 4,
                "X.,XX,XX X...,XXXX XXX X,X XX XX,XX,.X,.X ..XX,XXX.");
        assertEquals("1,0 0,1 0,1 2,2 1,0 1,0 0,2", service.solve(board, pieces));
    }
}
