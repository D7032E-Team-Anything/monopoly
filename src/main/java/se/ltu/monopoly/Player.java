package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.ownable.Ownable;
import se.ltu.monopoly.Tiles.Tile;

import java.util.ArrayList;

public class Player {

    private String name;
    private int position;
    private int money = 200;
    private int knowledge;

    private boolean computer;

    private ArrayList<Tile> ownedTiles = new ArrayList<Tile>();

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
            ownedTiles = new ArrayList<Tile>();

    }

    /**
     *
     * @param money is the amont of money to remove
     */
    public void getPayed(int money) {
        this.money += money;
    }

    public void pay(int money) {
        this.money -= money;
    }

    /**
     *
     * @param tile is the tile to be bought
     */
    public void buyTile(Ownable tile) {
        ownedTiles.add(tile);
        this.money -= tile.getPrice();
    }

    /**
     *
     * @param position is position to be moved to
     */
    public void moveTo(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
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
     * @param knowledge is amount of knowledge to increase
     */
    public void increaseKnowledge(int knowledge) {
        this.knowledge += knowledge;
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

    public boolean isWin() {
        return win;
    }

    public void win() {
        this.win = true;
    }

    public boolean isBroke() {
        return money <= 0;
    }

    public boolean isComputer() {
        return computer;
    }

    public int getMoney() {
        return money;
    }
}
