package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class Exam implements Action, Tile{


    private String message;
    private String name;
    private int position;

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

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
