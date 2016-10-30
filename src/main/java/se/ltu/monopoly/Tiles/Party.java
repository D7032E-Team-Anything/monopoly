
package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class Party implements Action, Tile{

    private String message;
    private String name;
    private int position;
    private int knowledge;
    private int pay;

    public Party(int position, String name,int knowledge, int pay) {

        this.position = position;
        this.knowledge = knowledge;
        this.name = name;
        this.pay = pay;
    }

    public void onAction(Player p, Board b) {

        boolean success = p.pay(pay);
        if (!success) {
            message = p.getmName() + " could not afford to pay for the party and has lost";
        }
        p.decreaseKnowledge(knowledge);

    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
