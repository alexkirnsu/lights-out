package com.lightout.service;

import com.lightout.domain.Board;
import com.lightout.domain.Piece;

import java.util.List;

public interface SolverService {
    String solve(Board board, List<Piece> pieces);
}
