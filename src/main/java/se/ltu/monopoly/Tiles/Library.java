package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class Library implements Action, Tile{

    private String name;
    private String message;
    private int position;

    /**
     * The Library class represent a library tile. When a player lands on the
     * library he/she acquire 8 of knowledge
     *
     * @param position ownable tile position
     * @param name     ownable tile name
     * */
    public Library(int position, String name){
        this.position = position;
        this.name = name;
    }

    public void onAction(Player p, Board b) {
        p.increaseKnowledge(8);
        message = p.getName() + " has landed in the library and got 8 of knowledge";
    }

    public String message() {
        return message;
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
