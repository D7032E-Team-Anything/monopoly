package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Exam implements Action{


    String message;

    public void onAction(Player p, Board b) {

        if(p.getKnowledge() >= 200) {
            b.setGameEnd(true);
            message = p.getName() + " PASSED THE EXAM AND WINS THE GAME! CONGRATULATIONS!";

        }

        p.skipTurn(true);
        message = p.getName() + " had not studied enough for the exam and have to take a re-exam. Skip one turn";


    }

    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return "Exam";
    }

}
