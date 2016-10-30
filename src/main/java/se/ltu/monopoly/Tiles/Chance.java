package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.chanceCards.ChanceCard;

import java.util.Random;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Chance implements Action {


    public void onAction(Player p, Board b) {

        int card = new Random().nextInt(5);
        Action action;
        switch (card){
            case 0:
                action = new ChanceCard("StudyAtLibrary", new ChanceCard.StudyAtLibrary());
                action.onAction(p, b);
                break;
            case 1:
                action = new ChanceCard("FallenIll", new ChanceCard.FallenIll());
                action.onAction(p, b);
                break;
            case 2:
                action = new ChanceCard("GotanExam", new ChanceCard.GotanExam());
                action.onAction(p, b);
                break;
            case 3:
                action = new ChanceCard("PWNZ", new ChanceCard.PWNZ());
                action.onAction(p, b);
                break;
            case 4:
                action = new ChanceCard("Party", new ChanceCard.Party());
                action.onAction(p, b);
                break;
        }


    }

    public String message() {
        return "";
    }

    @Override
    public String toString() {
        return "Chance";
    }
}
