package se.ltu.code;

import java.util.ArrayList;

class Player {

    private String name;
    private int position;
    private int money = 200;
    private int knowledge;
    private boolean computer;
    private ArrayList<Integer> ownsTile = new ArrayList<Integer>();
    private boolean stillPlaying = true;
    private boolean skipOneTurn;

    public Player(String name) {
        //Add spaces to make the gameboard printout pretty
        this.setName("   " + name + "   ");
    }
    public Player(String name, boolean computer) {
        //Add spaces to make the gameboard printout pretty
        this.setName("   " + name + "   ");
        this.setComputer(computer);
    }

    public void setStillPlaying(boolean playing) {
        {
            stillPlaying = playing;
            setName("         ");
            setOwnsTile(new ArrayList<Integer>());
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
    public boolean isSkipOneTurn() {
        return skipOneTurn;
    }
    public void setSkipOneTurn(boolean skipOneTurn) {
        this.skipOneTurn = skipOneTurn;
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
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
}

