package se.ltu.monopoly.Tiles;


import se.ltu.monopoly.Action;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.Board;
/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Start implements Action{


    public void onAction(Player p, Board b) {

        p.getPayed(40);

    }

    public String message() {
        return null;
    }

    @Override
    public String toString() {
        return "Start";
    }

}
