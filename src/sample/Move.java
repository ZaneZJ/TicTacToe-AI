package sample;

public class Move {


    static int row;
    static int col;

    static char player = 'x';
    static char opponent = 'o';

    static Boolean isMovesLeft (char board[][]) {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == '_') {

                    return true;

                }
            }
        }

        return false;

    }

    static int evaluate (char b[][]) {

        for (int row = 0; row < 3; row++) {

            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {

                if (b[row][0] == player) {

                    return +10;

                } else if (b[row][0] == opponent) {

                    return -10;

                }
            }
        }

        for (int col = 0; col < 3; col++) {

            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {

                if (b[0][col] == player) {

                    return +10;

                } else if (b[0][col] == opponent) {

                    return -10;

                }
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {

            if (b[0][0] == player) {

                return +10;

            } else if (b[0][0] == opponent) {

                return -10;

            }
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {

            if (b[0][2] == player) {

                return +10;

            } else if (b[0][2] == opponent) {

                return -10;

            }
        }

        return 0;
    }

    int[] findBestMove(char board[][]) {

        Minimax mm = new Minimax();

        int bestVal = -1000;
        row = -1;
        col = -1;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == '_') {

                    board[i][j] = player;
                    int moveVal = mm.minimax(board, 0, false);
                    board[i][j] = '_';

                    if (moveVal > bestVal) {

                        row = i;
                        col = j;
                        bestVal = moveVal;

                    }
                }
            }
        }

        int [] arr = {row + 1, col + 1};

        System.out.printf("The value of the best Move " + "is : %d\n\n", bestVal);

        return arr;

    }

}
