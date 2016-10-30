package se.ltu.monopoly;

import se.ltu.monopoly.chanceCards.ChanceCard;

import java.util.ArrayList;


public class BoardBuilder {

    Board board;

    private Dice dice;
    private ArrayList<Player> players = new ArrayList<Player>();
    private ArrayList<Action> tiles = new ArrayList<Action>();
    private ArrayList<Action> chanceCards = new ArrayList<Action>();

    public void setDice(int sides) {
        dice = new Dice(sides);
    }

    public void setPlayers(String[] playerNames) {
        int computerID = 1;
        for(int i=0; i<4; i++) {
            if(playerNames.length >= (i+1)) {
                players.add(new Player(playerNames[i]));
            } else {
                //C.1 = computer 1, etc.
                players.add(new Player("C." + computerID, true));
                computerID++;
            }
        }
    }

    public void setChanceCards() {

        chanceCards.add(new ChanceCard("StudyAtLibrary", new ChanceCard.StudyAtLibrary()));
        chanceCards.add(new ChanceCard("FallenIll", new ChanceCard.FallenIll()));
        chanceCards.add(new ChanceCard("GotanExam", new ChanceCard.GotanExam()));
        chanceCards.add(new ChanceCard("PWNZ", new ChanceCard.PWNZ()));
        chanceCards.add(new ChanceCard("Party", new ChanceCard.Party()));

    }

    public void setTiles(ArrayList<Action> tiles) {
        this.tiles = tiles;
    }

    public Board createBoard() {
        return new Board(players, tiles, chanceCards, dice);
    }




}
