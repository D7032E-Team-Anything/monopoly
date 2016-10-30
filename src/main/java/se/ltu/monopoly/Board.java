package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.Tile;
import se.ltu.monopoly.Tiles.ownable.Ownable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Board {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList<Player> players;
    private ArrayList<Tile> tiles;
    private Dice dice;
    private Gui gui;
    private boolean gameEnd = false;

    public Board(ArrayList<Player> mPlayers, ArrayList<Tile> mTiles, Dice dice) {
        this.players = mPlayers;
        this.tiles = mTiles;
        this.dice = dice;
        this.gui = new Gui(players);
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

        Tile currentTile = tiles.get(currentPos);

        if (currentTile instanceof Ownable) {

            Ownable ownableTile = (Ownable) currentTile;

            if (!ownableTile.hasOwner() && !player.isComputer()) {

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
        Action currentAction = (Action) currentTile;
        currentAction.onAction(player, this);
        System.out.println(currentAction.message());

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
        return tiles.get(pos);
    }
    public boolean isGameEnd() {
        return gameEnd;
    }
    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

}
