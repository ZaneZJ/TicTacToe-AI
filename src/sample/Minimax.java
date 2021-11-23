package sample;

public class Minimax {

//    static int minimax(char board[][], int depth, Boolean isMax) {
//
//        Move move = new Move();
//
//        int score = move.evaluate(board);
//
//        if (score == 10) {
//            return score;
//        }
//
//        if (score == -10) {
//            return score;
//        }
//
//        if (move.areMovesLeft(board) == false) {
//            return 0;
//        }
//
//        if (isMax) {
//
//            int best = -1000;
//
//            for (int i = 0; i < 3; i++) {
//
//                for (int j = 0; j < 3; j++) {
//
//                    if (board[i][j]=='_') {
//
//                        board[i][j] = move.ai;
//                        best = Math.max(best, minimax(board, depth + 1, !isMax));
//                        board[i][j] = '_';
//
//                    }
//                }
//            }
//
//            return best;
//
//        }
//
//        else {
//            int best = 1000;
//
//            for (int i = 0; i < 3; i++) {
//
//                for (int j = 0; j < 3; j++) {
//
//                    if (board[i][j] == '_') {
//
//                        board[i][j] = move.player;
//                        best = Math.min(best, minimax(board, depth + 1, !isMax));
//                        board[i][j] = '_';
//
//                    }
//                }
//            }
//
//            return best;
//
//        }
//    }

    private static int evaluate(char board[][], int depth) {

        int xSum = 0;
        int oSum = 0;

        // Check row
        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                if (board[row][col] == 'x') {
                    xSum++;
                } else if (board[row][col] == 'o') {
                    oSum++;
                }

            }

            if (xSum == 3) {
                return 10 + depth;
            } else if (oSum == 3) {
                return -10 - depth;
            }

            xSum = 0;
            oSum = 0;
        }

        // Check col
        for (int col = 0; col < 3; col++) {

            for (int row = 0; row < 3; row++) {

                if (board[row][col] == 'x') {
                    xSum++;
                } else if (board[row][col] == 'o') {
                    oSum++;
                }

            }

            if (xSum == 3) {
                return 10 + depth;
            } else if (oSum == 3) {
                return -10 - depth;
            }

            xSum = 0;
            oSum = 0;
        }

        // Check diagonal
        for (int i = 0; i < 3; i++) {

            if (board[i][i] == 'x') {
                xSum++;
            } else if (board[i][i] == 'o') {
                oSum++;
            }

        }

        if (xSum == 3) {
            return 10 + depth;
        } else if (oSum == 3) {
            return -10 - depth;
        }

        xSum = 0;
        oSum = 0;

        // Check anti-diagonal
        for (int i = 0; i < 3; i++) {

            if (board[i][2 - i] == 'x') {
                xSum++;
            } else if (board[i][2 - i] == 'o') {
                oSum++;
            }

        }

        if (xSum == 3) {
            return 10 + depth;
        } else if (oSum == 3) {
            return -10 - depth;
        }

        return 0;

    }

    public static int minimax(char board[][], int depth, boolean isMax, int min, int max) {

        int boardVal = evaluate(board, depth);

        if (Math.abs(boardVal) > 0 || depth == 0 || !Move.areMovesLeft(board)) {
            return boardVal;
        }

        if (isMax) {

            int valueH = Integer.MIN_VALUE;

            for (int row = 0; row < 3; row++) {

                for (int col = 0; col < 3; col++) {

                    if (Move.isValidMove(row, col)) {

                        Move.setMarkAt(row, col, 'x');
                        valueH = Math.max(valueH, minimax(board, depth - 1, false, min, max));
                        Move.setMarkAt(row, col, '_');
                        min = Math.max(min, valueH);
                        if (min >= max) {
                            return valueH;
                        }

                    }
                }
            }

            return valueH;

        } else {

            int valueL = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {

                for (int col = 0; col < 3; col++) {

                    if (Move.isValidMove(row, col)) {
                        Move.setMarkAt(row, col, 'o');
                        valueL = Math.min(valueL, minimax(board, depth - 1, true, min, max));
                        Move.setMarkAt(row, col, '_');
                        max = Math.min(max, valueL);
                        if (max <= min) {
                            return valueL;
                        }
                    }
                }
            }

            return valueL;
        }

    }

    public static int[] findBestMove(char board[][]) {

        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                if (board[row][col] == '_') {

                    Move.setMarkAt(row, col, 'x');
                    int moveValue = minimax(board, 12,false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    Move.setMarkAt(row, col, '_');

                    if (moveValue > bestValue) {

                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue = moveValue;

                    }
                }
            }
        }

        return bestMove;

    }

}
