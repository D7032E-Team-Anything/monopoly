package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Classroom extends Ownable {


    String roomNumber;
    int knowledge;

    public Classroom(String roomNumber, int rent, int knowledge, int price) {
        super(rent, price);
        this.knowledge = knowledge;
        this.roomNumber = roomNumber;
    }

    @Override
    public void doAction(Player p) {
        super.doAction(p);

        p.increaseKnowledge(this.knowledge);

    }

    @Override
    public String toString() {
        return roomNumber;
    }
}
