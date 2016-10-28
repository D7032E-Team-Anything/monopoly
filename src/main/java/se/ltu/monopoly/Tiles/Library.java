package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 28/10/16.
 */
public class Library implements Tile{

    public void doAction(Player p) {
        p.increaseKnowledge(8);
    }

    public String message() {
        return "";
    }

    @Override
    public String toString() {
        return "Library";
    }
}
