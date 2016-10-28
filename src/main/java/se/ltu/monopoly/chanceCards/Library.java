package se.ltu.monopoly.chanceCards;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.chanceCards.ChanceCard;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class Library implements ChanceCard {

    private final String TITLE = "has decided to cram for the exam in the LIBRARY this turn and the next";

    public void onAction(Player p, Board b) {

        System.out.println(p.getName() + " " + TITLE);

        p.increaseKnowledge(32);
        p.moveTo(7);
        p.skipTurn(true);

    }


}