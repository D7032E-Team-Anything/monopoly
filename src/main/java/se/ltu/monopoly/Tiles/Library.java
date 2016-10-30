package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class Library implements Action, Tile{

    private String name;
    private int position;

    public Library(int position, String name){
        this.position = position;
        this.name = name;
    }

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

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
