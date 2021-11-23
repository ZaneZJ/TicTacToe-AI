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

    private static int evaluate(char board[][]) {

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
                return 1;
            } else if (oSum == 3) {
                return -2;
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
                return 1;
            } else if (oSum == 3) {
                return -2;
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
            return 1;
        } else if (oSum == 3) {
            return -2;
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
            return 1;
        } else if (oSum == 3) {
            return -2;
        }

        return 0;

    }

    public static int minimax(char board[][], int depth, boolean isMax) {

        Move move = new Move();
        int boardVal = evaluate(board);

        if (depth == 0 || !move.areMovesLeft(board)) {
            return boardVal;
        }

        if (isMax) {

            int value = 0;

            for (int row = 0; row < 3; row++) {

                for (int col = 0; col < 3; col++) {

                    if (move.isValidMove(row, col)) {

                        move.setMarkAt(row, col, 'x');
                        value =+ minimax(board, depth - 1, false) * depth;
                        move.setMarkAt(row, col, '_');

                    }
                }
            }

            return value;

        } else {

            int value = 0;

            for (int row = 0; row < 3; row++) {

                for (int col = 0; col < 3; col++) {

                    if (move.isValidMove(row, col)) {
                        move.setMarkAt(row, col, 'o');
                        value =+ minimax(board, depth - 1, true) * depth;
                        move.setMarkAt(row, col, '_');
                    }
                }
            }

            return value;
        }

    }

    public static int[] findBestMove(char board[][]) {

        Move move = new Move();

        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                if (board[row][col] == '_') {

                    move.setMarkAt(row, col, 'x');
                    int moveValue = minimax(board, 6,false);
                    move.setMarkAt(row, col, '_');

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
