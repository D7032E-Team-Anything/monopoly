package se.ltu.monopoly;

/**
 * Created by haidar on 2016-10-27.
 */
public class Main {

    /**
     * Parsing argv input and initializing new monopoly game
     * @param argv is initials of the players
     */

    public static void main(String[] argv) {


        if(argv.length < 1 ||argv.length > 4) {

            System.out.println("Syntax: Monopoly [Player 1-4 initials]\nexample: Monopoly F.L J.H K.S");
            System.exit(0);

        } else {

            Player[] players    = parsePlayers(argv);
            //LTUGameBoard board  = new LTUGameBoard();

            //new Monopoly(board, players, 6);

        }

    }

    /**
     *
     * @param playerNames
     * @return list of players
     */
    private static Player[] parsePlayers(String[] playerNames) {

        Player[] players    = new Player[4];

        int computerID = 1;
        for(int i=0; i<4; i++) {
            if(playerNames.length >= (i+1)) {
                players[i] = new Player(playerNames[i]);
            } else {
                //C.1 = computer 1, etc.
                players[i] = new Player("C." + computerID, true);
                computerID++;
            }
        }

        return players;
    }
}
