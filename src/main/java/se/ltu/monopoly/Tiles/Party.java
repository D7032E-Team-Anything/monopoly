package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class Party implements Action, Tile{

    String message;
    private String name;
    private int position;
    private int pay, knowledge;

    public Party(int position, String name,int knowledge, int pay) {

        this.position = position;
        this.name = name;
        this.knowledge = knowledge;
        this.pay = pay;
    }

    public void onAction(Player p, Board b) {

        boolean success = p.pay(pay);

        if (!success) {
            message = p.getName() + " could not afford to pay for the party and has lost";
        }

        p.decreaseKnowledge(knowledge);

    }

    public String message() {
        return "";
    }

    @Override
    public String toString() {
        return "Party";
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
