package com.position;

public interface OutterPosition {

    OutterPosition move(int idx);
    boolean isGameEnd();
    boolean isWinFor(char turn);
    int bestMove();

}
