package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Philm extends Ownable {

    public Philm(int rent, int price) {
        super(rent, price);
    }

    @Override
    public void doAction(Player p) {
        super.doAction(p);
    }

    @Override
    public String toString() {
        return "Philm";
    }

}
