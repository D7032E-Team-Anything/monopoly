package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class StartT extends Tile {

    public StartT(String name) {
        super(name);
    }

    public void doAction(Player p, Board b) {

        p.getPayed(40);

    }
}
