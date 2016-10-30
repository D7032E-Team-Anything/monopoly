package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.Tile;


import java.util.ArrayList;


public class BoardBuilder {

    Board board;

    private Dice dice;
    private ArrayList<Player> players       = new ArrayList<Player>();
    private ArrayList<Tile> tiles           = new ArrayList<Tile>();
    private ArrayList<Action> chanceCards   = new ArrayList<Action>();

    /**
     * Creates all players for the game
     * @param playerNames is players to be created
     */
    public void setPlayers(String[] playerNames) {
        int computerID = 1;
        for(int i=0; i<4; i++) {
            if(playerNames.length >= (i+1)) {
                players.add(new Player(playerNames[i]));
            } else {
                //C.1 = computer 1, etc.
                players.add(new Player("C." + computerID, true));
                computerID++;
            }
        }
    }

    /**
     * Creates and returns the board
     * @return board
     */
    public Board createBoard() {
        return new Board(players, tiles, dice);
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
    public void setDice(int sides) {
        dice = new Dice(sides);
    }

}
