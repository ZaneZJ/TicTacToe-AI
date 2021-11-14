package sample;

public class Game {

    public static void main(String[] args) {

        char board[][] = {{ 'x', 'o', 'o' },
                { 'o', 'x', 'x' },
                { '_', '_', '_' }};

        Move m = new Move();

        int [] bestMove = m.findBestMove(board);

        System.out.printf("The Optimal Move is :\n");
        System.out.printf("ROW: %d COL: %d\n\n",
                bestMove[0], bestMove[1] );
    }

}
