package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.Tile;
import se.ltu.monopoly.chanceCards.ChanceCard;
import se.ltu.monopoly.chanceCards.ChanceCardFactory;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by haidar on 2016-10-27.
 */
public class Board {

    private ArrayList<Player> players;
    private ArrayList<Tile> tiles;
    private ArrayList<ChanceCard> chanceCards;
    private Dice mDice;

    public Board(ArrayList<Player> mPlayers, ArrayList<Tile> mTiles, ArrayList<ChanceCard> mChanceCards, Dice mDice) {
        this.players = mPlayers;
        this.tiles = mTiles;
        this.chanceCards = mChanceCards;
        this.mDice = mDice;
    }

    public void start() {

        chanceCards.get(0).onAction(players.get(0), this);

    }


}
