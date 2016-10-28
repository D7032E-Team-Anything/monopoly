package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Party implements Tile{


    private int pay, knowledge;

    public Party(int knowledge, int pay) {

        this.knowledge = knowledge;
        this.pay = pay;
    }

    public void doAction(Player p) {

        p.pay(pay);
        p.decreaseKnowledge(knowledge);

    }

    @Override
    public String toString() {
        return "Party";
    }

}
