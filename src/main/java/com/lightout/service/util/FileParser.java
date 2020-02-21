package com.lightout.service.util;

import com.lightout.domain.Board;
import com.lightout.domain.Piece;

import java.util.*;

public class FileParser {
    private static final String ROW_DELIMITER = ",";
    private static final String PIECE_DELIMITER = " ";

    private static final Set<Integer> validDepths = new HashSet<>(Arrays.asList(2, 3, 4));

    public static Board parseBoard(int depth, String stringToParse) {
        if (!validDepths.contains(depth)) {
            throw new IllegalArgumentException(
                    String.format("Depth should be equal to 2, 3 or 4 but found [%s]", depth));
        }

        int[][] boardConfiguration = parseBoardConfiguration(stringToParse);
        if (isNotRectangular(boardConfiguration)) {
            throw new IllegalArgumentException("Board should be a rectangular");
        }
        return new Board(depth, boardConfiguration);
    }

    private static int[][] parseBoardConfiguration(String stringToParse) {
        String[] rows = stringToParse.split(ROW_DELIMITER);
        int[][] result = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            result[i] = parseBoardRow(rows[i]);
        }
        return result;
    }

    private static int[] parseBoardRow(String stringToParse) {
        int rowLength = stringToParse.length();
        int[] result = new int[rowLength];
        int row = Integer.parseInt(stringToParse);

        for (int j = rowLength - 1; j >= 0; j--) {
            result[j] = (byte) (row % 10);
            row /= 10;
        }
        return result;
    }

    private static boolean isNotRectangular(int[][] configuration) {
        int rowLength = configuration[0].length;

        for (int i = 1; i < configuration.length; i++) {
            if (rowLength != configuration[i].length) {
                return true;
            }
        }
        return false;
    }

    public static List<Piece> parsePieces(int boardHeight, int boardWidth, String stringToParse) {
        String[] pieceLines = stringToParse.split(PIECE_DELIMITER);
        List<Piece> pieces = new ArrayList<>(pieceLines.length);

        for (int i = 0; i < pieceLines.length; i++) {
            int[][] pieceConfiguration = parsePieceConfiguration(pieceLines[i]);
            checkPieceConfiguration(i, pieceConfiguration, boardHeight, boardWidth);
            pieces.add(new Piece(pieceConfiguration, boardHeight, boardWidth));
        }
        return pieces;
    }

    private static int[][] parsePieceConfiguration(String stringToParse) {
        String[] rows = stringToParse.split(ROW_DELIMITER);
        int[][] result = new int[rows.length][];

        for (int i = 0; i < rows.length; i++) {
            result[i] = parsePieceRow(rows[i]);
        }
        return result;
    }

    private static void checkPieceConfiguration(int pieceNumber, int[][] configuration, int maxHeight, int maxWidth) {
        if (isNotRectangular(configuration)) {
            throw new IllegalArgumentException(
                    String.format("Piece should be a rectangular, but [%d] isn't", pieceNumber));
        }
        if (configuration.length > maxHeight || configuration[0].length > maxWidth) {
            throw new IllegalArgumentException(
                    String.format("Piece should be less than board, but [%d] isn't", pieceNumber));
        }
    }

    private static int[] parsePieceRow(String rowToParse) {
        int rowLength = rowToParse.length();
        int[] result = new int[rowLength];

        for (int i = 0; i < rowLength; i++) {
            char symbol = rowToParse.charAt(i);
            if (!isValidSymbol(symbol)) {
                throw new IllegalArgumentException("Only valid symbols for piece are \".\" and \"X\" ");
            }
            result[i] = rowToParse.charAt(i) == 'X' ? 1 : 0;
        }
        return result;
    }

    private static boolean isValidSymbol(char symbol) {
        return symbol == 'X' || symbol == '.';
    }
}
