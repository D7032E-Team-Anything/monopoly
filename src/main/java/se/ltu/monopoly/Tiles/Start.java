package se.ltu.monopoly.Tiles;


import se.ltu.monopoly.Action;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.Board;

public class Start implements Action, Tile{

    private String name;
    private int position;

    public Start(int position, String name){
        this.position = position;
        this.name = name;
    }
    public void onAction(Player p, Board b) {
        p.increaseMoney(40);
    }

    public String getMessage() {
        return null;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
