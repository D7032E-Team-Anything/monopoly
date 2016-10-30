package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.*;
import se.ltu.monopoly.Tiles.Ownable;

import java.util.ArrayList;


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
            add(new Start   (0, "Start"));
            add(new Chance  (2, "Chance"));
            add(new Party   (4, "Party",8, 18));
            add(new Library (7, "Library"));
            add(new Chance  (9, "Chance"));
            add(new Exam    (11,"Exam"));
            add(new Ownable (1, "Stil"      ,2 , 6 ,0));
            add(new Ownable (3, "Film"      ,2 , 6 ,0));
            add(new Ownable (5, "A109"      ,10, 3, 3));
            add(new Ownable (6, "A117"      ,10, 3, 3));
            add(new Ownable (8, "B234Ske"   ,10, 3, 3));
            add(new Ownable (10,"E632"      ,10, 3, 3));
            add(new Ownable (12,"A209"      ,20, 5, 4));
            add(new Ownable (13,"A210"      ,20, 5, 4));
        }});
        bb.setDice(6);

        // start board
        Board b = bb.createBoard();
        b.start();
    }
}
