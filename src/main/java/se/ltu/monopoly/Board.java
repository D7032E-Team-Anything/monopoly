package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.Tile;
import se.ltu.monopoly.chanceCards.ChanceCard;

import java.util.Vector;

/**
 * Created by haidar on 2016-10-27.
 */
public class Board {
    private Dice mDice;
    private Vector<Player> mPlayers;
    private Vector<Tile> mTiles;
    private Vector<ChanceCard> mChanceCards;

    public Dice getmDice() {
        return mDice;
    }

    public void setmDice(Dice mDice) {
        this.mDice = mDice;
    }

    public Vector<Player> getmPlayers() {
        return mPlayers;
    }

    public void setmPlayers(Vector<Player> mPlayers) {
        this.mPlayers = mPlayers;
    }

    public Vector<Tile> getmTiles() {
        return mTiles;
    }

    public void setmTiles(Vector<Tile> mTiles) {
        this.mTiles = mTiles;
    }

    public Vector<ChanceCard> getmChanceCards() {
        return mChanceCards;
    }

    public void setmChanceCards(Vector<ChanceCard> mChanceCards) {
        this.mChanceCards = mChanceCards;
    }
}
