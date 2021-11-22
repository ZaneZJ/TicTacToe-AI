package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.xml.soap.Text;

public class Controller {

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
        move.xTurn = false;
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

        textWon.setValue(text);

    }

    public void start() {

        main.setVisible(true);
        boardTicTacToe.setVisible(false);
        endOfTheGame.setVisible(false);

    }

    public void play() {

        BoardTTT.cleanBoard();

        main.setVisible(false);
        boardTicTacToe.setVisible(true);
        endOfTheGame.setVisible(false);

        do {

            move.insert("Row : ", "Col : ");

            if (BoardTTT.gameOver == true) {

                if (BoardTTT.winner == 'x') {

                    System.out.println("X won!");
                    endOfGame("AI WON !");

                } else if (BoardTTT.winner == 'o') {

                    System.out.println("O won!");
                    endOfGame("Player WON !");

                } else {

                    System.out.println("Even!");
                    endOfGame("Even !");

                }

            }

        } while (BoardTTT.gameOver == true);

    }

}
