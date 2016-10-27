package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.Tiles.ownable.OwnableT;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Classroom extends OwnableT {


    public Classroom(String name, int rent, int knowledge, int price) {
        super(name, rent, knowledge, price);
    }

    @Override
    public void doAction(Player p, Board b) {
        super.doAction(p, b);

        p.increaseKnowledge(this.knowledge);

    }
}
