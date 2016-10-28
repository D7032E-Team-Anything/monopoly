package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.NormalTile;
import se.ltu.monopoly.Tiles.Tile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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

            ArrayList<NewPlayer> players = new ArrayList<NewPlayer>(Arrays.asList(parsePlayers(argv)));

            Dice dice = new Dice(6);

            ArrayList<Tile> tiles = new ArrayList<Tile>();
            // getOrPay , buy, rent, knowledge, name
            Tile start      = new NormalTile(40 , 0 , 0, 0  , "Start"   , false);
            Tile stil       = new NormalTile(0  , 6 , 2, 0  , "Stil"    , true);
            Tile chance1    = new NormalTile(0  , 0 , 0, 0  , "chance1" , false);
            Tile philm      = new NormalTile(0  , 6 , 2, 0  , "Philm"   , true);
            Tile party      = new NormalTile(-18, 0 , 0, -8 , "Party"   , false);
            Tile a109       = new NormalTile(0  , 10, 3, 3  , "A109"    , true);
            Tile a117       = new NormalTile(0  , 10, 3, 3  , "A117"    , true);
            Tile library    = new NormalTile(0  , 0 , 0, 8  , "Library" , false);
            Tile b234Ske    = new NormalTile(0  , 10, 3, 3  , "B234Ske" , true);
            Tile chance2    = new NormalTile(0  , 0 , 0, 0  , "chance2" , false);
            Tile e632       = new NormalTile(0  , 10, 3, 3  , "E632"    , false);
            Tile exam       = new NormalTile(0  , 0 , 0, 0  , "Exam"    , false);
            Tile a209       = new NormalTile(0  , 20, 5, 4  , "A209"    , true);
            Tile a210       = new NormalTile(0  , 20, 5, 4  , "A210"    , true);

            tiles.add(start);
            tiles.add(stil);
            tiles.add(chance1);
            tiles.add(philm);
            tiles.add(party);
            tiles.add(a109);
            tiles.add(a117);
            tiles.add(library);
            tiles.add(b234Ske);
            tiles.add(chance2);
            tiles.add(e632);
            tiles.add(exam);
            tiles.add(a209);
            tiles.add(a210);

            Board board = new Board();

            board.setmDice      (dice);
            board.setmPlayers   (players);
            board.setmTiles     (tiles);

            board.setRunning(true);
            board.start();

            //LTUGameBoard board  = new LTUGameBoard();

            //new Monopoly(board, players, 6);

        }

    }

    /**
     *
     * @param playerNames
     * @return list of players
     */
    private static NewPlayer[] parsePlayers(String[] playerNames) {

        NewPlayer[] players    = new NewPlayer[4];

        int computerID = 1;
        for(int i=0; i<4; i++) {
            if(playerNames.length >= (i+1)) {
                players[i] = new NewPlayer(playerNames[i]);
            } else {
                //C.1 = computer 1, etc.
                players[i] = new NewPlayer("C." + computerID, true);
                computerID++;
            }
        }

        return players;
    }
}
