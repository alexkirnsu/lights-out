package com.lightout.service.impl;

import com.lightout.domain.Board;
import com.lightout.domain.Piece;
import com.lightout.domain.Point;
import com.lightout.service.SolverService;
import com.lightout.service.util.PointStateIterator;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class SolverServiceImpl implements SolverService {

    private static final String POINT_DELIMITER = " ";

    public String solve(Board board, List<Piece> pieces) {
        PointStateIterator iterator = new PointStateIterator(pieces);

        int[][] zeroState = new int[board.getHeight()][board.getWidth()];
        while (iterator.hasNext()) {
            Point[] topLeftPoints = iterator.next();
            for (int i = 0; i < topLeftPoints.length; i++) {
                board.apply(pieces.get(i), topLeftPoints[i]);
            }
            if (board.isEqualState(zeroState)) {
                return convertToString(topLeftPoints);
            }
            board.refreshCurrentState();
        }

        throw new RuntimeException("Solution is not found");
    }

    private String convertToString(Point[] points) {
        StringJoiner joiner = new StringJoiner(POINT_DELIMITER);
        Arrays.stream(points).forEach(p -> joiner.add(p.toString()));
        return joiner.toString();
    }
}
