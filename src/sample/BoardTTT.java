package sample;

import java.util.Scanner;

public class BoardTTT {

    static char [][] board;
    static char winner;
    static boolean gameOver;

    Move move = new Move();

    static void playAgain() {

        Scanner insert = new Scanner(System.in);
        System.out.println("Do you want to play again? yes/no");
        String play = insert.nextLine();

        if (play == "yes") {
            cleanBoard();
            gameOver = false;
        }

    }

    static void cleanBoard() {

        for (int row = 0; row < 3; row++) {

            for (int col = 0; col < 3; col++) {

                board[row][col] = '_';

            }
        }

        Move.whoWillStart();

    }

}
