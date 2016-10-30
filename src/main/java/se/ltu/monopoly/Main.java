package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.*;
import se.ltu.monopoly.Tiles.ownable.Classroom;
import se.ltu.monopoly.Tiles.ownable.Philm;
import se.ltu.monopoly.Tiles.ownable.Stil;

import java.util.ArrayList;

/**
 * Created by haidar on 2016-10-27.
 */
public class Main {

    /**
     * Parsing argv input and initializing new monopoly game
     * @param argv is initials of the players
     */

    public static void main(String[] argv) {

        // check system arguments
        if(argv.length < 1 ||argv.length > 4) {
            System.out.println("Syntax: Monopoly [Player 1-4 initials]\nexample: Monopoly F.L J.H K.S");
            System.exit(0);
        }


        // board settings
        BoardBuilder bb = new BoardBuilder();
        bb.setPlayers(argv);
        bb.setTiles(new ArrayList<Action>() {{
            add(new Start());
            add(new Stil(2,6));
            add(new Chance());
            add(new Philm(2,6));
            add(new Party(8, 18));
            add(new Classroom("A109", 3, 3, 10));
            add(new Classroom("A117", 3, 3, 10));
            add(new Library());
            add(new Classroom("B234Ske", 3, 3, 10));
            add(new Chance());
            add(new Classroom("E632", 3, 3, 10));
            add(new Exam());
            add(new Classroom("A209", 5, 4, 20));
            add(new Classroom("A210", 5, 4, 20));
        }});
        bb.setDice(6);

        // start board
        bb.createBoard().start();


    }

}
