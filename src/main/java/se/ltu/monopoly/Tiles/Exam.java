package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class Exam implements Action, Tile{


    private int position;
    private String message;
    private String name;

/**
 * The Exam class represent an exam tile. The player will win the game if
 * he/she has more than 200 of knowledge.
 *
 * @param position ownable tile position
 * @param name     ownable tile name
 * */

    public Exam(int position, String name){
        this.position = position;
        this.name = name;
    }

    public void onAction(Player p, Board b) {

        if(p.getKnowledge() >= 200) {
            b.setGameEnd(true);
            message = p.getmName() + " PASSED THE EXAM AND WINS THE GAME! CONGRATULATIONS!";
        }
        p.skipTurn(true);
        message = p.getmName() + " had not studied enough for the exam and have to take a re-exam. Skip one turn";
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
