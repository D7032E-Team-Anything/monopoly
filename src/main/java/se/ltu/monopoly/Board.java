package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.Tile;
import se.ltu.monopoly.Tiles.Ownable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Board extends Thread{

    private BufferedReader bufferedReader;
    private ArrayList<Player> players;
    private ArrayList<Tile> tiles;
    private Dice dice;
    private Gui gui;
    private boolean gameEnd;

    public Board(ArrayList<Player> mPlayers, ArrayList<Tile> mTiles, Dice dice) {
        this.players = mPlayers;
        this.tiles = mTiles;
        this.dice = dice;
        this.gui = new Gui(players);
        this.bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
    }

    public void run() {
        gui.printInstructions();
        int i = 0;
        while(!isGameEnd()){
            makeMove(players.get(i));
            i++;
            if(i>=players.size()) {
                i=0;
            }
        }
    }


    private void makeMove(Player player) {

        // Check if still playing
        if(!player.isStillPlaying()) {
            setGameEnd(isSomeonePlaying());
            if(isGameEnd()) System.out.println("There are no more players, everyone lost");
            return;
        }

        // Player skip turn
        if(player.isSkipTurn()) {
            player.skipTurn(false);
            System.out.println(player.getmName() + " skips one turn");
            return;
        }

        System.out.println("It is now the turn of " + player.getmName());

        // Player character - let them press enter before their turn begins.
        if(!player.isComputer()) {
            gui.paintGameBoard();
            System.out.println(player.getmName() + "Press [enter] to continue. (study-time: "+
                    player.getMoney()+", knowledge: " + player.getKnowledge()+ ")");
            try{
                bufferedReader.readLine();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        // What to buy?
        int currentPos   = player.getPosition();

        Tile currentTile = getTile(currentPos);

        if (currentTile instanceof Ownable) {

            Ownable ownableTile = (Ownable) currentTile;

            if (!ownableTile.hasOwner() && !player.isComputer()) {

                String answer = gui.wantToBuy(player, ownableTile);

                if(answer.equals("y")) {

                    if (ownableTile.buyTile(player)) {

                        System.out.println(player.getmName() + "bought " + ownableTile.getName());

                    } else {

                        System.out.println("You can not afford " + ownableTile.getName());

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
        currentTile = getTile(player.getPosition());
        System.out.println(player.getmName() + "rolls a " + roll + " and lands on " + currentTile.getName());

        // Execute tile action
        Action currentAction = (Action) currentTile;
        currentAction.onAction(player, this);
        System.out.println(currentAction.getMessage());

        // Print status of player
        System.out.println(player.getStatus());

    }

    /**
     *
     * @return true if someone is still playing, false otherwise
     */
    private boolean isSomeonePlaying() {
        boolean someoneIsStillPlaying = false;
        for(Player p : players) {
            if(p.isStillPlaying()) {
                someoneIsStillPlaying = true;
            }
        }
        return someoneIsStillPlaying;
    }

    /**
     * Used to remove owner of specified room
     * @param roomName is room name to be removed
     */
    public void removeOwner(String roomName){
        for(Player p : players){
            if(p.ownsTile(roomName)){
               p.removeTile(roomName);
                return;
            }
        }
    }

    public Tile getTile(int pos){
        for(Tile t : tiles){
            if(t.getPosition() == pos){
                return t;
            }
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
