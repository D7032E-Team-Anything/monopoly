package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Start implements Tile {

    public void doAction(Player p) {

        p.getPayed(40);

    }

    @Override
    public String toString() {
        return "Start";
    }

}
