package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Classroom extends Ownable {


    private String roomName;
    private int knowledge;

    public Classroom(String roomName, int rent, int knowledge, int price) {
        super(rent, price);
        this.knowledge = knowledge;
        this.roomName = roomName;
    }

    @Override
    public void onAction(Player p, Board b) {
        super.onAction(p,b);
        p.increaseKnowledge(this.knowledge);


    }

    @Override
    public String toString() {
        return roomName;
    }


    @Override
    public String message() {
        return "";
    }

}
