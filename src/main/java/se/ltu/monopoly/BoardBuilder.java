package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.Tile;
import se.ltu.monopoly.chanceCards.ChanceCard;
import se.ltu.monopoly.chanceCards.ChanceCardFactory;

import java.util.ArrayList;


public class BoardBuilder {

    Board board;

    private Dice dice;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Tile> tiles = new ArrayList<Tile>();
    private ArrayList<ChanceCard> chanceCards = new ArrayList<ChanceCard>();

    public void setDice(int sides) {
        dice = new Dice(sides);
    }

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

    public void setChanceCards(String[] cards) {

        ChanceCardFactory ccf = new ChanceCardFactory();

        for(int i = 0; i<cards.length; i++) {

            ChanceCard chanceCard = null;

            try {
                chanceCard = ccf.getChanceCard(cards[i]);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            chanceCards.add(chanceCard);

        }

    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public Board createBoard() {
        return new Board(players, tiles, chanceCards, dice);
    }




}
