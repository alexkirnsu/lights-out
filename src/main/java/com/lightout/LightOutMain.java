package com.lightout;

import com.lightout.domain.Board;
import com.lightout.domain.Piece;
import com.lightout.service.impl.SolverServiceImpl;
import com.lightout.service.util.FileParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LightOutMain {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            int depth = Integer.parseInt(reader.readLine());
            Board board = FileParser.parseBoard(depth, reader.readLine());
            List<Piece> pieces = FileParser.parsePieces(board.getHeight(), board.getWidth(), reader.readLine());

            System.out.println(new SolverServiceImpl().solve(board, pieces));
        }
    }
}
