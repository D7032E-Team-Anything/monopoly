package se.ltu.monopoly.Tiles;


import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class ChanceCard implements Action {

    /**
     * All chance cards are structured as followed
     *  1. Action: contains an action that effects the player or board in some way
     *  2. Message: getMessage that will be displayed to the user
     */

    private static final String STUDY_AT_LIBRARY =
            " has decided to cram for the exam in the LIBRARY this turn and the next";
    private static final String GOT_SICK = "has fallen ill. Go to START without collecting any study-time";
    private String name;
    private Action action;


    public ChanceCard(String name, Action action) {

        this.name = name;
        this.action = action;
    }

    static public class StudyAtLibrary implements Action {
        private String message;
        public StudyAtLibrary() {}
        public void onAction (Player p, Board b) {
            message = p.getmName() + STUDY_AT_LIBRARY;
            p.increaseKnowledge(32);
            p.moveTo(7);
            p.skipTurn(true);
        }
        public String getMessage() {
            return message;
        }
    }

    static public class FallenIll implements Action { // case 1
        private String message;
        public FallenIll() {}
        public void onAction (Player p, Board b) {
            message = GOT_SICK;
            p.moveTo(0);
        }
        public String getMessage() {
            return message;
        }
    }

    static public class GotanExam implements Action { // case 2
        private String message;
        public GotanExam() {}
        public void onAction (Player p, Board b) {
            message = p.getmName() + "has been given a VERBAL EXAM and moves " +
                    "to EXAM without losing a turn";
            p.moveTo(11);
            if(p.getKnowledge() >= 200) {
                message += "\n"+p.getmName() + " PASSED THE EXAM AND WINS " +
                        "THE GAME! CONGRATULATIONS!";
                b.setGameEnd(true); // exit 0
            }
        }
        public String getMessage() {
            return message;
        }

    }

    static public class PWNZ implements Action { // case 3
        private String message;
        public PWNZ() {}
        public void onAction (Player p, Board b) {

            message = p.getmName() + "\"PWNZ\" at the workshops.\n" +
                    p.getmName() + "gains ownership of A209 and A210, moves to A209 and collects the knowledge";

            b.removeOwner("A209");
            b.removeOwner("A210");

            p.gainTile((Ownable) b.getTile(12));
            p.gainTile((Ownable) b.getTile(13));
            ((Action) b.getTile(12)).onAction(p,b);
            p.moveTo(12);

        }
        public String getMessage() {
            return message;
        }

    }

    static public class Party implements Action { // case 4
        private String message;
        public Party() {}

        public void onAction(Player p, Board b) {
            message = p.getmName() + " moves to PARTY, pays the costs, decreases knowledge, and skips one turn";
            p.moveTo(4);
            p.skipTurn(true);
            p.decreaseKnowledge(8);
            p.decreaseMoney(18);

            if (p.getMoney() < 0) {
                // exit 1. Lose the game if you are all out of study-time
                message += "\n" +
                        p.getmName() + " Could not afford to pay for the party and has lost";
                p.lose(true); // exit 1

            }

        }
        public String getMessage() {
            return message;
        }
    }

    public void onAction (Player p, Board b) {
        action.onAction(p, b);
    }
    public String getMessage() {
        return action.getMessage();
    }

}
