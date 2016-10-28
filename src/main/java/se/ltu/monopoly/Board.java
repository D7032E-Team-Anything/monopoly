package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.Tile;
import se.ltu.monopoly.chanceCards.ChanceCard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by haidar on 2016-10-27.
 */
public class Board {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private ArrayList<Player> players;
    private ArrayList<Tile> tiles;
    private ArrayList<ChanceCard> chanceCards;
    private Dice mDice;
    private Gui gui;

    private boolean gameEnd = false;

    public Board(ArrayList<Player> mPlayers, ArrayList<Tile> mTiles, ArrayList<ChanceCard> mChanceCards, Dice mDice) {
        this.players = mPlayers;
        this.tiles = mTiles;
        this.chanceCards = mChanceCards;
        this.mDice = mDice;
        this.gui = new Gui(this, players);
    }

    public void start() {

        gui.printInstructions();

        int i = 0;
        while (!gameEnd) {


            makeMove(players.get(i));

            i++;
            if(i>=players.size()) {
                i=0;
            }

        }

    }

    private void makeMove(Player player) {

        /*
			Rules:
				* 1. In the beginning of your turn you may opt to buy unowned property
				* 2. Roll d6 dice and move that number of steps.
				* 3. If the tile you land on is owned, pay the rent
				* 4. Increase or decrease your knowledge accordingly.
				* 5. Pay any costs involved in arriving to the tile (e.g. PARTY)
				* 6. Draw a Chance card on the CHANCE tile and follow the text
				* exit 1. Lose the game if you are all out of study-time
				* exit 2. Win the game if you have >= 200 knowledge and stand at EXAM
				* Property can not be sold once bought
		*/


        if(!player.isStillPlaying()) {
            gameEnd = isSomeonePlaying();
            if(gameEnd) System.out.println("There are no more players, everyone lost");
            return;
        }

        System.out.println("It is now the turn of " + player.getName());

        // Player character - let them press enter before their turn begins.
        if(!player.isComputer()) {
            //Print the game-board for player characters.
            gui.paintGameBoard();
            System.out.println(player.getName() + "Press [enter] to continue. (study-time: "+
                    player.getMoney()+", knowledge: " + player.getKnowledge()+ ")");
            try{
                bufferedReader.readLine();
            } catch(Exception e){};
        }








    }

    private boolean isSomeonePlaying() {
        boolean someoneIsStillPlaying = false;
        for(Player p : players) {
            if(p.isStillPlaying()) {
                someoneIsStillPlaying = true;
            }
        }
        return someoneIsStillPlaying;
    }






}
