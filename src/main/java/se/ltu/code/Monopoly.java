package se.ltu.code;


import java.io.BufferedReader;
import java.io.InputStreamReader;

class Monopoly {

    // Used to read input form user
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    // Game variables
    private Dice dice;
    private LTUGameBoard board;
    private Player players[];

    public Monopoly(LTUGameBoard board, Player[] players, int sides) {

        this.players    = players;
        this.board      = board;
        this.dice       = new Dice(sides);

        board.paintGameBoard(players);

    }

    private void makeMove(Player player) {

    }

    private void drawChanceCard(Player player) {

    }

    private Player checkOwned(int pos) {
        for(int i=0; i<players.length; i++) {
            if(players[i].getOwnsTile().contains(new Integer(pos))) {
                return players[i];
            }
        }
        return null;
    }

}
