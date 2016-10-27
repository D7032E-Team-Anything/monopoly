package se.ltu.monopoly.chanceCards;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.chanceCards.ChanceCard;

/**
 * Created by erikuusitalo on 27/10/16.
 */
public class LibraryChanceCard extends ChanceCard {

    private String title = "has decided to cram for the exam in the LIBRARY this turn and the next";

    public void onAction(Player p, Board b) {

        System.out.println(title);

        p.setKnowledge(p.getKnowledge() + 32);
        p.setPosition(7);
        p.setSkipOneTurn(true);

    }


}
