package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Party implements Tile{

    String message;
    private int pay, knowledge;

    public Party(int knowledge, int pay) {

        this.knowledge = knowledge;
        this.pay = pay;
    }

    public void doAction(Player p) {

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

}
