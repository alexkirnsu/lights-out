package com.lightout.service.util;

import com.lightout.domain.Piece;
import com.lightout.domain.Point;

import java.util.Iterator;
import java.util.List;

public class PointStateIterator implements Iterator<Point[]> {
    private List<Piece> pieces;
    private Point[] points;
    private int[] cursor;
    private int [] limits;

    public PointStateIterator(List<Piece> pieces) {
        this.pieces = pieces;
        int size = pieces.size();
        this.cursor = new int[size];
        this.limits = new int[size];
        this.points = new Point[size];
        for (int i = 0; i < size; i++) {
            this.limits[i] = pieces.get(i).getTopLeftPointCount();
        }
    }

    @Override
    public boolean hasNext() {
        return cursor[0] != limits[0];
    }

    @Override
    public Point[] next() {
        fillPoints();
        doStep();
        return points;
    }

    private void doStep() {
        for (int i = cursor.length - 1; i >= 0 ; i--) {
            if (++cursor[i] < limits[i]) {
                break;
            }
            cursor[i] = i != 0 ? 0 : cursor[i];
        }
    }

    private void fillPoints() {
        for (int i = 0; i < cursor.length; i++) {
            points[i] = pieces.get(i).getTopLeftPoint(cursor[i]);
        }
    }
}
