package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.ownable.Ownable;

import java.util.ArrayList;
import java.util.Iterator;

public class Player {

    private String name;
    private int position;
    private int money;
    private int knowledge;
    private boolean computer;
    private boolean stillPlaying;
    private boolean skipTurn;
    private ArrayList<Ownable> ownedTiles;

    public Player(String name) {
        this(name, false);
    }

    public Player(String name, boolean computer) {

        this.name           = "   " + name + "   ";
        this.computer       = computer;
        this.stillPlaying   = true;
        this.ownedTiles     = new ArrayList<Ownable>();
        this.money          = 200;
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

    /**
     *
     * @param pay is the amount to pay
     * @return true if payment was successful, false otherwise
     */
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
     * @param steps is steps to move forward
     */
    public void move(int steps) {

        this.position += steps;

        if(this.position > 13) {
            this.position -= 14;
        }

    }

    /**
     *
     * @param name of tile
     * @return true if player owns tile, false otherwise
     */
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

    /**
     *
     * @return string representing the status of the player
     */
    public String getStatus() {
        return this.name + " has " + this.money + " study-time and " + this.knowledge + " knowledge";
    }

    public void increaseKnowledge(int knowledge) {
        this.knowledge += knowledge;
    }
    public void decreaseKnowledge(int knowledge) {
        this.knowledge -= knowledge;
    }
    public void decreaseMoney(int money) {
        this.money -= money;
    }
    public void skipTurn(boolean skipTurn) {
        this.skipTurn = skipTurn;
    }
    public void moveTo(int position) {
        this.position = position;
    }

    public boolean isStillPlaying() {
        return stillPlaying;
    }
    public boolean isComputer() {
        return computer;
    }
    public boolean isSkipTurn() {
        return skipTurn;
    }

    public int getKnowledge() {
        return knowledge;
    }
    public String getName() {
        return name;
    }
    public int getPosition() {
        return position;
    }
    public int getMoney() {
        return money;
    }


}
