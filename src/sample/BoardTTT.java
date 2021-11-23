package sample;

public class BoardTTT {

    static char [][] board = { {'_','_','_'},{'_','_','_'},{'_','_','_'} };
    static char winner;
    static boolean gameOver;

    static void playAgain(String text) {

        System.out.println("Do you want to play again? " + text);

        if (text == "yes") {
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
    }

}
