package sample;

public class Move {

    public static char ai = 'x';
    public static char player = 'o';
    public static char empty = '_';

    public static boolean xTurn;

    public static void setMarkAt(int row, int col, char mark) {
        BoardTTT.board[row][col] = mark;
    }

    public static void insert(String text1, String text2, int row, int col) {

        System.out.println(row + " " + col);

        if (areMovesLeft(BoardTTT.board) == false) {

            BoardTTT.gameOver = true;
            return;

        }

        if (xTurn == true) {

            if (isValidMove(row,col) == true) {

                System.out.println("X turn (AI)");
                placeMark(row, col);

            }

        } else {

            if (isValidMove(row,col) == true) {

                System.out.println("O turn (PLAYER)");
                placeMark(row, col);

            }
        }
    }

    public static Boolean isValidMove(int row, int col) {

        if (row >= 0 && row < 3 && col >= 0 && col < 3) {

            if (BoardTTT.board[row][col] == empty) {
                return true;
            }
        }
        return false;
    }

    public static Boolean areMovesLeft(char board[][]) {

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                if (board[row][col] == empty) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean checkIfWon(char turn, char board[][]) {

        // Check row
        for (int row = 0; row < 3; row++) {

            if (board[row][0] == turn && board[row][1] == turn && board[row][2] == turn) {
                return true;
            }

        }

        // Check column
        for (int col = 0; col < 3; col++) {

            if (board[0][col] == turn && board[1][col] == turn && board[2][col] == turn) {
                return true;
            }

        }

        // Check diagonal
        if (board[0][0] == turn && board[1][1] == turn && board[2][2] == turn) {
            return true;
        }


        // Check anti-diagonal
        if (board[0][2] == turn && board[1][1] == turn && board[2][0] == turn) {
            return true;
        }

        return false;
    }

    public static void placeMark(int row, int col) {

        if (xTurn == true) {

            BoardTTT.board[row][col] = ai;
            xTurn = false;

            if (checkIfWon(ai, BoardTTT.board) == true) {

                BoardTTT.gameOver = true;
                BoardTTT.winner = ai;

            }

        } else {

            BoardTTT.board[row][col] = player;
            xTurn = true;

            if (checkIfWon(player, BoardTTT.board) == true) {

                BoardTTT.gameOver = true;
                BoardTTT.winner = player;

            }
        }
    }
}
