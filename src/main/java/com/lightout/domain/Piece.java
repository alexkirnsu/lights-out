package com.lightout.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Piece {
    private final int[][] structure;
    private List<Point> topLeftPoints;

    public Piece(int[][] structure, int boardHeight, int boardWidth) {
        this.structure = structure;
        this.topLeftPoints = calculateTopLeftPoints(boardHeight, boardWidth);
    }

    private List<Point> calculateTopLeftPoints(int boardHeight, int boardWidth) {
        List<Point> result = new ArrayList<>();
        for (int i = 0; i <= boardHeight - getHeight(); i++) {
            for (int j = 0; j <= boardWidth - getWidth(); j++) {
                result.add(new Point(j, i));
            }
        }
        return result;
    }

    public boolean isEqualStructure(int[][] structure) {
        for (int i = 0; i < structure.length; i++) {
            if (!Arrays.equals(this.structure[i], structure[i])) {
                return false;
            }
        }
        return true;
    }

    public Point getTopLeftPoint(int number) {
        return topLeftPoints.get(number);
    }

    public int getTopLeftPointCount() {
        return topLeftPoints.size();
    }

    protected int getHeight() {
        return structure.length;
    }

    protected int getWidth() {
        return structure[0].length;
    }

    protected int getElement(int x, int y) {
        return structure[y][x];
    }
}
