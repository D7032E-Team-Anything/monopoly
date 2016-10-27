package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Philm extends OwnableT{

    public Philm(String name, int rent, int knowledge, int price) {
        super(name, rent, knowledge, price);
    }

    @Override
    public void doAction(Player p, Board b) {
        super.doAction(p, b);
    }

}
