package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

import java.util.Random;


public class Chance implements Action, Tile{

    private String message;
    private String name;
    private int position;

    /**
     * This tile represent a chance tile. When a player land on a chance
     * tile, a random car is selected and executed
     *
     * @param position chance tile position
     * @param name     chance tile name
     * */
    public Chance(int position, String name){
        this.position = position;
        this.name = name;
    }

    public void onAction(Player p, Board b) {

        int card = new Random().nextInt(5);
        Action action;
        switch (card){
            case 0:
                action = new ChanceCard("StudyAtLibrary", new ChanceCard.StudyAtLibrary());
                action.onAction(p, b);
                message = action.getMessage();
                break;
            case 1:
                action = new ChanceCard("FallenIll", new ChanceCard.FallenIll());
                action.onAction(p, b);
                message = action.getMessage();
                break;
            case 2:
                action = new ChanceCard("GotanExam", new ChanceCard.GotanExam());
                action.onAction(p, b);
                message = action.getMessage();
                break;
            case 3:
                action = new ChanceCard("PWNZ", new ChanceCard.PWNZ());
                action.onAction(p, b);
                message = action.getMessage();
                break;
            case 4:
                action = new ChanceCard("Party", new ChanceCard.Party());
                action.onAction(p, b);
                message = action.getMessage();
                break;
        }
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
