package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by haidar on 2016-10-27.
 */
public abstract class Tile {

    private String name;

    public Tile(String name) {
        this.name = name;
    }

    public abstract void doAction(Player p, Board b);

    public String getName() {
        return name;
    }

}
