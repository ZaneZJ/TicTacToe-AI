package sample;

import java.util.Scanner;

public class Move {

    static int row;
    static int col;

    static char ai = 'x';
    static char player = 'o';

    static boolean xTurn;

    BoardTTT boardTTT = new BoardTTT();

    static void whoWillStart() {

        Scanner insert = new Scanner(System.in);
        System.out.println("Who will start? x/o");
        String who = String.valueOf(insert.nextLine());

        if (who == "x") {
            xTurn = true;
        } else {
            xTurn = false;
        }
    }

    static void insert(String text1, String text2) {

        if (xTurn == true) {
            System.out.println("X turn (AI)");
        } else {
            System.out.println("O turn (PLAYER)");
        }

        Scanner insert = new Scanner(System.in);
        System.out.println(text1);
        int newRow = Integer.parseInt(insert.nextLine());
        System.out.println(text2);
        int newCol = Integer.parseInt(insert.nextLine());
        placeMark(newRow, newCol);

    }

    static Boolean isValidMove(int row, int col) {

        if (row >= 0 && row < 3 && col >= 0 && col < 3) {

            if (BoardTTT.board[row][col] == '_') {
                return true;
            }
        }
        return false;
    }

    static Boolean areMovesLeft(char board[][]) {

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                if (BoardTTT.board[row][col] == '_') {
                    return true;
                }
            }
        }
        return false;
    }

    static Boolean checkIfWon(char turn, char board[][]) {

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
        if (board[1][1] == turn && board[2][2] == turn && board[3][3] == turn) {
            return true;
        }


        // Check anti-diagonal
        if (board[1][3] == turn && board[2][2] == turn && board[3][1] == turn) {
            return true;
        }

        return false;
    }

    static void placeMark(int row, int col) {

        if (areMovesLeft(BoardTTT.board) == false) {

            BoardTTT.gameOver = true;

        }

        if (isValidMove(row, col) == false) {

            insert("Reinsert row : ", "Reinsert col : ");

        }

        if (xTurn == true) {

            BoardTTT.board[row][col] = 'x';
            xTurn = false;

            if (checkIfWon('x', BoardTTT.board) == true) {

                BoardTTT.gameOver = true;
                BoardTTT.winner = 'x';

            }

        } else {

            BoardTTT.board[row][col] = 'o';
            xTurn = false;

            if (checkIfWon('o', BoardTTT.board) == true) {

                BoardTTT.gameOver = true;
                BoardTTT.winner = 'o';

            }
        }
    }

    static int evaluate (char b[][]) {

        for (int row = 0; row < 3; row++) {

            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {

                if (b[row][0] == ai) {

                    return +10;

                } else if (b[row][0] == player) {

                    return -10;

                }
            }
        }

        for (int col = 0; col < 3; col++) {

            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {

                if (b[0][col] == ai) {

                    return +10;

                } else if (b[0][col] == player) {

                    return -10;

                }
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {

            if (b[0][0] == ai) {

                return +10;

            } else if (b[0][0] == player) {

                return -10;

            }
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {

            if (b[0][2] == ai) {

                return +10;

            } else if (b[0][2] == player) {

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

                    board[i][j] = ai;
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
