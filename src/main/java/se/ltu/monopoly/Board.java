package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.ownable.Ownable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by haidar on 2016-10-27.
 */
public class Board {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private ArrayList<Player> players;
    private ArrayList<Action> tiles;
    private ArrayList<Action> chanceCards;
    private Dice dice;
    private Gui gui;

    private boolean gameEnd = false;

    public Board(ArrayList<Player> mPlayers, ArrayList<Action> mTiles, ArrayList<Action> mChanceCards, Dice dice) {
        this.players = mPlayers;
        this.tiles = mTiles;
        this.chanceCards = mChanceCards;
        this.dice = dice;
        this.gui = new Gui(this, players);
    }

    public void start() {

        gui.printInstructions();

        int i = 0;
        while (!isGameEnd()) {


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

        // Check if still playing
        if(!player.isStillPlaying()) {
            setGameEnd(isSomeonePlaying());
            if(isGameEnd()) System.out.println("There are no more players, everyone lost");
            return;
        }

        // Player skip turn
        if(player.isSkipTurn()) {
            player.skipTurn(false);
            System.out.println(player.getName() + " skips one turn");
            return;
        }

        System.out.println("It is now the turn of " + player.getName());

        // Player character - let them press enter before their turn begins.
        if(!player.isComputer()) {
            gui.paintGameBoard();
            System.out.println(player.getName() + "Press [enter] to continue. (study-time: "+
                    player.getMoney()+", knowledge: " + player.getKnowledge()+ ")");
            try{
                bufferedReader.readLine();
            } catch(Exception e){};
        }


        // What to buy?
        int currentPos   = player.getPosition();
        Action currentTile = tiles.get(currentPos);

        if (currentTile instanceof Ownable) {

            Ownable ownableTile = (Ownable) currentTile;

            if ( !ownableTile.hasOwner() && !player.isComputer()) {

                String answer = gui.wantToBuy(player, ownableTile);

                if(answer.equals("y")) {

                    if (ownableTile.buyTile(player)) {

                        System.out.println(player.getName() + "bought " + ownableTile.toString());

                    } else {

                        System.out.println("You can not afford " + ownableTile.toString());

                    }

                }

            }
            else if(!ownableTile.hasOwner() && player.isComputer()) {
                ownableTile.buyTile(player);
            }

        }




        // Roll dice
        int roll = dice.roll();
        player.move(roll);

        currentTile = tiles.get(player.getPosition());
        System.out.println(player.getName() + "rolls a " + roll + " and lands on " + currentTile.toString());

        // Execute tile action
        currentTile.onAction(player, this);

        System.out.println(currentTile.message());


        System.out.println(player.getStatus());


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
    public void removeOwner(String roomName){

        for(Player p : players){
            if(p.ownsTile(roomName)){
               p.removeTile(roomName);
                return;
            }
        }
    }

    // crazy fix
    public Ownable getTile(int pos){
        Action tile = tiles.get(pos);

        if (tile instanceof Ownable){
            return (Ownable) tile;
        }
        return null;
    }


    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }
}
