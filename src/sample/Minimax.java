package sample;

public class Minimax {

    static int minimax(char board[][], int depth, Boolean isMax) {

        Move move = new Move();

        int score = move.evaluate(board);

        if (score == 10) {
            return score;
        }

        if (score == -10) {
            return score;
        }

        if (move.areMovesLeft(board) == false) {
            return 0;
        }

        if (isMax) {

            int best = -1000;

            for (int i = 0; i < 3; i++) {

                for (int j = 0; j < 3; j++) {

                    if (board[i][j]=='_') {

                        board[i][j] = move.ai;
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

                        board[i][j] = move.player;
                        best = Math.min(best, minimax(board, depth + 1, !isMax));
                        board[i][j] = '_';

                    }
                }
            }

            return best;

        }
    }
}
