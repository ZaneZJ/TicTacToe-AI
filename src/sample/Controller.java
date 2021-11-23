package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.util.Random;

public class Controller extends AnchorPane {

    @FXML
    private Text textWon;

    @FXML
    private Pane main;

    @FXML
    private Pane endOfTheGame;

    @FXML
    private GridPane boardTicTacToe;

    @FXML
    private Button buttonAI;

    @FXML
    private Button buttonPlayer;

    @FXML
    private Button buttonYes;

    @FXML
    private Button buttonNo;

    @FXML
    private Button button00;

    @FXML
    private Button button01;

    @FXML
    private Button button02;

    @FXML
    private Button button10;

    @FXML
    private Button button11;

    @FXML
    private Button button12;

    @FXML
    private Button button20;

    @FXML
    private Button button21;

    @FXML
    private Button button22;

    BoardTTT boardTTT = new BoardTTT();
    Move move = new Move();
    Minimax minimax = new Minimax();
    Random random = new Random();
    boolean endGame = false;
    boolean beginWithAI = false;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        BoardTTT.playAgain("no");
        Stage stage = (Stage) buttonNo.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handlePlayAgain(ActionEvent event) {
        BoardTTT.playAgain("yes");
        start();
    }

    @FXML
    public void startAI(ActionEvent event) {
        move.xTurn = true;
        beginWithAI = true;
        play();
    }

    @FXML
    public void startPlayer(ActionEvent event) {
        move.xTurn = false;
        play();
    }

    public void endOfGame(String text) {

        main.setVisible(false);
        boardTicTacToe.setVisible(false);
        endOfTheGame.setVisible(true);

        textWon.setText(text);
        endGame = true;

    }

    public void start() {

        main.setVisible(true);
        boardTicTacToe.setVisible(false);
        endOfTheGame.setVisible(false);

    }

    public void end() {

        if (BoardTTT.gameOver == true) {

            if (BoardTTT.winner == 'x') {

                System.out.println("X won!");
                endOfGame("AI WON!");

            } else if (BoardTTT.winner == 'o') {

                System.out.println("O won!");
                endOfGame("Player WON!");

            } else {

                System.out.println("Even!");
                endOfGame("Even!");

            }
        }

    }

    public void play() {

        BoardTTT.cleanBoard();

        main.setVisible(false);
        boardTicTacToe.setVisible(true);
        endOfTheGame.setVisible(false);

        if (beginWithAI == true) {
            beginWithAI = false;
            move.insert("Row : ", "Col : ", random.nextInt(3), random.nextInt(3));
            drawBoard();
        }

        button00.setOnAction((buttonEventCalc) -> {
            move.insert("Row : ", "Col : ", 0, 0);
            turnOfAI();
        });

        button01.setOnAction((buttonEventCalc) -> {
            move.insert("Row : ", "Col : ", 0, 1);
            turnOfAI();
        });

        button02.setOnAction((buttonEventCalc) -> {
            move.insert("Row : ", "Col : ", 0, 2);
            turnOfAI();
        });

        button10.setOnAction((buttonEventCalc) -> {
            move.insert("Row : ", "Col : ", 1, 0);
            turnOfAI();
        });

        button11.setOnAction((buttonEventCalc) -> {
            move.insert("Row : ", "Col : ", 1, 1);
            turnOfAI();
        });

        button12.setOnAction((buttonEventCalc) -> {
            move.insert("Row : ", "Col : ", 1, 2);
            turnOfAI();
        });

        button20.setOnAction((buttonEventCalc) -> {
            move.insert("Row : ", "Col : ", 2, 0);
            turnOfAI();
        });

        button21.setOnAction((buttonEventCalc) -> {
            move.insert("Row : ", "Col : ", 2, 1);
            turnOfAI();
        });

        button22.setOnAction((buttonEventCalc) -> {
            move.insert("Row : ", "Col : ", 2, 2);
            turnOfAI();
        });

    }

    public void turnOfAI() {

        if (Move.xTurn == true) {

            int[] bestMove = minimax.findBestMove(BoardTTT.board);
            move.insert("Row : ", "Col : ", bestMove[0], bestMove[1]);

        }

        drawBoard();

    }

    public void drawBoard() {

        setValue(0,0, button00);
        setValue(0,1, button01);
        setValue(0,2, button02);
        setValue(1,0, button10);
        setValue(1,1, button11);
        setValue(1,2, button12);
        setValue(2,0, button20);
        setValue(2,1, button21);
        setValue(2,2, button22);

    }

    public void setValue(int row, int col, Button button) {

        char value = BoardTTT.board[row][col];

        if (value == 'x') {
            button.setText("X");
        } else if (value == 'o') {
            button.setText("O");
        } else {
            button.setText("");
        }

    }

}
