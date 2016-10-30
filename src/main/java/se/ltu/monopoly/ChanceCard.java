package se.ltu.monopoly;


import se.ltu.monopoly.Tiles.ownable.Ownable;


public class ChanceCard implements Action { // case 0

    /**
     * All chance cards are structured as followed
     *  1. Action: contains an action that effects the player or board in some way
     *  2. Message: message that will be displayed to the user
     */

    private String name;
    private Action action;


    public ChanceCard(String name, Action action) {
        super();
        this.name = name;
        this.action = action;
    }

    static public class StudyAtLibrary implements Action {
        private String message;
        public StudyAtLibrary() {};
        public void onAction (Player p, Board b) {
            message = p.getName() + "has decided to cram for " +
                    "the exam in the LIBRARY this turn and the next";
            p.increaseKnowledge(32);
            p.moveTo(7);
            p.skipTurn(true);
        }
        public String message() {
            return message;
        }
    }

    static public class FallenIll implements Action { // case 1
        private String message;
        public FallenIll() {};
        public void onAction (Player p, Board b) {
            message = "has fallen ill. Go to START without collecting " +
                    "any study-time";
            p.moveTo(0);
        }
        public String message() {
            return message;
        }
    }

    static public class GotanExam implements Action { // case 2
        private String message;
        public GotanExam() {};
        public void onAction (Player p, Board b) {
            message = p.getName() + "has been given a VERBAL EXAM and moves " +
                    "to EXAM without losing a turn";
            p.moveTo(11);
            if(p.getKnowledge() >= 200) {
                message += "\n"+p.getName() + " PASSED THE EXAM AND WINS " +
                        "THE GAME! CONGRATULATIONS!";
                b.setGameEnd(true); // exit 0
            }
        }
        public String message() {
            return message;
        }

    }

    static public class PWNZ implements Action { // case 3
        private String message;
        public PWNZ() {};
        public void onAction (Player p, Board b) {

            message = p.getName() + "\"PWNZ\" at the workshops.\n" +
                    p.getName() + "gains ownership of A209 and A210, moves to A209 and collects the knowledge";

            b.removeOwner("A209");
            b.removeOwner("A210");

            p.gainTile((Ownable) b.getTile(12));
            p.gainTile((Ownable) b.getTile(13));
            ((Action) b.getTile(12)).onAction(p,b);
            p.moveTo(12);

        }
        public String message() {
            return message;
        }


    }

    static public class Party implements Action { // case 4
        private String message;
        public Party() {};

        public void onAction(Player p, Board b) {
            message = p.getName() + " moves to PARTY, pays the costs, decreases knowledge, and skips one turn";
            p.moveTo(4);
            p.skipTurn(true);
            p.decreaseKnowledge(8);
            p.decreaseMoney(18);

            if (p.getMoney() < 0) {
                // exit 1. Lose the game if you are all out of study-time
                message += "\n" +
                        p.getName() + " Could not afford to pay for the party and has lost";
                p.lose(true); // exit 1

            }

        }
        public String message() {
            return message;
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void onAction (Player p, Board b) {
        action.onAction(p, b);
    }
    public String message() {
        return action.message();
    }

}
