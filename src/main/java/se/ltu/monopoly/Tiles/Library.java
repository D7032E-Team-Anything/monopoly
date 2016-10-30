package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 28/10/16.
 */
public class Library implements Action{

    public void onAction(Player p, Board b) {
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
