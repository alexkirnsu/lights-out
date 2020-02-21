package com.lightout.service.util;

import com.lightout.domain.Piece;
import com.lightout.domain.Point;

import java.util.Iterator;
import java.util.List;

public class PointStateIterator implements Iterator<Point[]> {
    private List<Piece> pieces;
    private Point[] points;
    private int[] configuration;
    private int [] limits;
    private boolean firstStep = true;

    public PointStateIterator(List<Piece> pieces) {
        this.pieces = pieces;
        int size = pieces.size();
        this.configuration = new int[size];
        this.limits = new int[size];
        this.points = new Point[size];
        for (int i = 0; i < size; i++) {
            this.limits[i] = pieces.get(i).getTopLeftPointCount();
        }
    }

    @Override
    public boolean hasNext() {
        if (firstStep) {
            firstStep = false;
            return true;
        }
        doStep();
        return configuration[0] != limits[0];
    }

    @Override
    public Point[] next() {
        fillPoints();
        return points;
    }

    private void doStep() {
        for (int i = configuration.length - 1; i >= 0 ; i--) {
            if (++configuration[i] < limits[i]) {
                break;
            }
            configuration[i] = i != 0 ? 0 : configuration[i];
        }
    }

    private void fillPoints() {
        for (int i = 0; i < configuration.length; i++) {
            points[i] = pieces.get(i).getTopLeftPoint(configuration[i]);
        }
    }
}
