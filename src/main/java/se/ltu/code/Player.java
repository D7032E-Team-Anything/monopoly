package se.ltu.code;

import java.util.ArrayList;

class Player {

    private String name;
    private int position = 0;
    private int money = 200;
    private int knowledge = 0;
    private boolean computer = false;
    private ArrayList<Integer> ownsTile = new ArrayList<Integer>();
    private boolean stillPlaying = true;
    private boolean skipOneTurn = false;

    /**
     *
     * @param name is name of player
     */
    public Player(String name) {
        //Add spaces to make the gameboard printout pretty
        this.name = "   " + name + "   ";
    }

    /**
     *
     * @param name is name of player
     * @param computer if player is computer or not
     */
    public Player(String name, boolean computer) {
        //Add spaces to make the gameboard printout pretty
        this.name = "   " + name + "   ";
        this.computer = computer;
    }

    /**
     * Checks if player is still playing, if not removes name and removes all owned tiles
     * @param playing
     */
    public void setStillPlaying(boolean playing) {
        if(!false) {
            stillPlaying = playing;
            name = "         ";
            ownsTile = new ArrayList<Integer>();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    public boolean isComputer() {
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    public ArrayList<Integer> getOwnsTile() {
        return ownsTile;
    }

    public void setOwnsTile(ArrayList<Integer> ownsTile) {
        this.ownsTile = ownsTile;
    }

    public boolean isStillPlaying() {
        return stillPlaying;
    }

    public boolean isSkipOneTurn() {
        return skipOneTurn;
    }

    public void setSkipOneTurn(boolean skipOneTurn) {
        this.skipOneTurn = skipOneTurn;
    }
}

