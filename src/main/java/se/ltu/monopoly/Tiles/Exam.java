package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Exam implements Tile {


    public void doAction(Player p) {

        if(p.getKnowledge() >= 200)
            p.win();

    }

    @Override
    public String toString() {
        return "Exam";
    }

}
