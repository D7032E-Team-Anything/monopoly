package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class PartyT extends Tile{


    private int pay, knowledge;

    public PartyT(String name, int knowledge, int pay) {
        super(name);
        this.knowledge = knowledge;
        this.pay = pay;
    }

    public void doAction(Player p, Board b) {

        p.pay(pay);
        p.decreaseKnowledge(knowledge);

    }

}
