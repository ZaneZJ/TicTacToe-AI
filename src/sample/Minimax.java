package sample;

public class Minimax {

    static int minimax(char board[][], int depth, Boolean isMax) {

        Move m = new Move();

        int score = m.evaluate(board);

        if (score == 10) {
            return score;
        }

        if (score == -10) {
            return score;
        }

        if (m.isMovesLeft(board) == false) {
            return 0;
        }

        if (isMax) {

            int best = -1000;

            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {

                    if (board[i][j]=='_') {

                        board[i][j] = m.player;
                        best = Math.max(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = '_';

                    }
                }
            }

            return best;

        }

        else {
            int best = 1000;

            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {

                    if (board[i][j] == '_') {

                        board[i][j] = m.opponent;
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = '_';

                    }
                }
            }

            return best;

        }
    }
}
