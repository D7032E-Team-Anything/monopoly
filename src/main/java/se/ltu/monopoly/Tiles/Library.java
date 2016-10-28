package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 28/10/16.
 */
public class Library implements Tile{

    public void doAction(Player p) {
        p.increaseKnowledge(8);
    }

    @Override
    public String toString() {
        return "Library";
    }
}
