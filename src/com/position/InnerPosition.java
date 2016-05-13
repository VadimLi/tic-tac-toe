package com.position;


import java.util.*;

public class InnerPosition {

    public static final int DIM = 3;
    public static final int SIZE = DIM*DIM;

    public char turn;
    public char[] board;

    protected Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

    public String toString() {
        return new String(board);
    }

    public InnerPosition unmove(int idx) {
        board[idx] = ' ';
        turn = turn == 'x' ? 'o' : 'x';
        return this;
    }

    protected boolean lineMatch(char turn, int start, int end, int step) {
        for (int i = start; i < end; i+=step) {
            if (board[i] != turn)
                return false;
        }
        return true;
    }

    public int blanks() {
        int total = 0;
        for (int i = 0; i < SIZE; i++) {
            if (board[i] == ' ') {
                total++;
            }
        }
        return total;
    }

    protected int code() {
        int value = 0;
        for (int i = 0; i < SIZE; i++) {
            value = value * 3;
            if (board[i] == 'x')
                value += 1;
            else if (board[i] == 'o')
                value += 2;
        }
        return value;
    }


}
