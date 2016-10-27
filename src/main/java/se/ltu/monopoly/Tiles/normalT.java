package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class normalT extends Tile {

    private boolean ownable;
    private int rent, buy, knowlage;

    public normalT(boolean ownable, int rent, int buy, int knowlage, String name) {
        super(name);
        this.ownable = ownable;
        this.rent = rent;
        this.buy = buy;
        this.knowlage = knowlage;
    }

    public void doAction(Player p, Board b) {



    }

}
