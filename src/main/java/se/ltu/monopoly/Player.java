package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.ownable.Ownable;

import java.util.ArrayList;
import java.util.Iterator;

public class Player {

    private String name;
    private int position;
    private int money = 200;
    private int knowledge;

    private boolean computer;

    private ArrayList<Ownable> ownedTiles = new ArrayList<Ownable>();

    private boolean stillPlaying = true;
    private boolean skipTurn;
    private boolean win = false;

    public Player(String name) {
        //Add spaces to make the gameboard printout pretty
        this.name = "   " + name + "   ";
    }

    public Player(String name, boolean computer) {
        //Add spaces to make the gameboard printout pretty
        this.name = "   " + name + "   ";
        this.computer = computer;
    }

    /**
     * Sets playing to false, removes name, clears owned tiles
     * @param playing
     */
    public void lose(boolean playing) {

            stillPlaying = playing;
            name = "         ";
            ownedTiles = new ArrayList<Ownable>();

    }

    /**
     *
     * @param money is the amont of money to remove
     */
    public void getPayed(int money) {
        this.money += money;
    }

    public boolean pay(int pay) {

        if (money - pay < 0) {
            lose(true);
            return false;
        }

        this.money -= pay;
        return true;

    }

    /**
     *
     * @param tile is the tile to be bought
     * @return true if buy is successful, false otherwise
     */
    public boolean buyTile(Ownable tile) {

        if (money - tile.getPrice() < 0) {
            return false;
        }

        ownedTiles.add(tile);
        this.money -= tile.getPrice();

        return true;
    }

    /**
     *
     * @param tile is the tile which the player gained without paying for it
     *
     */
    public void gainTile(Ownable tile) {
        ownedTiles.add(tile);
    }

    /**
     *
     * @param steps
     */
    public void move(int steps) {

        this.position += steps;

        if(this.position > 13) {
            this.position -= 14;
        }


    }

    public boolean ownsTile(String name){
        for(Ownable t : ownedTiles){
            if(t.toString().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param tileName is the tile to be removed
     */
    public void removeTile(String tileName){
        Iterator<Ownable> it = ownedTiles.iterator();
        while (it.hasNext()) {
            Ownable tile = it.next();
            if (tile.toString().equals(tileName)) {
                it.remove();
            }
        }
    }

    public void moveTo(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    /**
     *
     * @param knowledge is amount of knowledge to increase
     */
    public void increaseKnowledge(int knowledge) {
        this.knowledge += knowledge;
    }

    /**
     *
     * @param knowledge is amount of knowledge to be removed
     */
    public void decreaseKnowledge(int knowledge) {
        this.knowledge -= knowledge;
    }

    /**
     *
     * @param money is amount of money to be removed
     */
    public void decreaseMoney(int money) {
        this.money -= money;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param skipTurn true if player should skip a turn, false to continue playing
     */
    public void skipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }

    /**
     *
     * @return true if player is still playing
     */
    public boolean isStillPlaying() {
        return stillPlaying;
    }

    public int getKnowledge() {
        return knowledge;
    }


    public boolean isComputer() {
        return computer;
    }

    public int getMoney() {
        return money;
    }

    public boolean isSkipTurn() {
        return skipTurn;
    }

    public String getStatus() {
        return this.name + " has " + this.money + " study-time and " + this.knowledge + " knowledge";
    }
}
