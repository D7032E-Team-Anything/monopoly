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
        bb.setTiles(new ArrayList<Tile>() {{
            add(new Start       (0, "Start"));
            add(new Stil        (1, "Stil"   ,2,6));
            add(new Chance      (2, "Chance"));
            add(new Philm       (3, "Philm"  ,2,6));
            add(new Party       (4, "Party"  ,8, 18));
            add(new Classroom   (5, "A109", 3, 3, 10));
            add(new Classroom   (6, "A117", 3, 3, 10));
            add(new Library     (7, "Library"));
            add(new Classroom   (8, "B234Ske", 3, 3, 10));
            add(new Chance      (9, "Chance"));
            add(new Classroom   (10,"E632", 3, 3, 10));
            add(new Exam        (11,"Exam"));
            add(new Classroom   (12,"A209", 5, 4, 20));
            add(new Classroom   (13,"A210", 5, 4, 20));
        }});
        bb.setDice(6);

        // start board
        bb.createBoard().start();


    }

}
