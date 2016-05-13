package com.position;

import java.util.*;

public class Position extends InnerPosition implements OutterPosition{

    public Position() {
        turn = 'x';
        board = new char[SIZE];
        for (int i = 0; i < SIZE; i++) {
            board[i] = ' ';
        }
    }

    public Position(String string, char turn) {
        board = string.toCharArray();
        this.turn = turn;
    }

    public Position move(int idx) {
        board[idx] = turn;
        turn = turn == 'x' ? 'o' : 'x';
        return this;
    }

    public List<Integer> possibleMoves() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i] == ' ') {
                list.add(i);
            }
        }
        return list;
    }

    public boolean isWinFor(char turn) {
        boolean isWin = false;
        for (int i = 0; i < SIZE; i+=DIM) {
            isWin = isWin || lineMatch(turn, i, i + DIM, 1);
        }
        for (int i = 0; i < DIM; i++) {
            isWin = isWin || lineMatch(turn, i, SIZE, DIM);
        }
        isWin = isWin || lineMatch(turn, 0, SIZE, DIM+1);
        isWin = isWin || lineMatch(turn, DIM-1, SIZE-1, DIM-1);
        return isWin;
    }

    public int bestMove() {

        Comparator<Integer> cmp = new Comparator<Integer>() {
            @Override
            public int compare(Integer first, Integer second) {
                int a = move(first).minimax();
                unmove(first);
                int b = move(second).minimax();
                unmove(second);
                return a - b;
            }
        };

        List<Integer> list = possibleMoves();
        return turn == 'x' ? Collections.max(list, cmp) : Collections.min(list, cmp);
    }

    public int minimax() {
        Integer key = code();
        Integer value = cache.get(key);
        if (value != null) return value;
        if(isWinFor('x')) return blanks() + 1;
        if(isWinFor('o')) return -blanks() - 1;
        if (blanks() == 0) return 0;
        List<Integer> list = new ArrayList<>();
        for (Integer idx : possibleMoves()) {
            list.add(move(idx).minimax());
            unmove(idx);
        }
        value = turn == 'x' ? Collections.max(list) : Collections.min(list);
        cache.put(key, value);
        return value;
    }

    public boolean isGameEnd() {
        return isWinFor('x') || isWinFor('o') || blanks() == 0;
    }


}
