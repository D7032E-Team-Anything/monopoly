package se.ltu.monopoly;

public class NewPlayer {

    private String  name;
    private int     position;
    private int     money;
    private int     knowledge;
    private boolean computer;
    private boolean stillPlaying;
    private boolean skipOneTurn;

    public NewPlayer(String name) {
        this("   " + name + "   ", false);
    }

    public NewPlayer(String name, boolean computer) {
        //Add spacesA to make the gameboard printout pretty
        this.setName("   " + name + "   ");
        this.computer = computer;
        this.setStillPlaying(true);
        this.setMoney(200);
    }

    public void setStillPlaying(boolean playing) {
            this.stillPlaying = playing;
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

    public boolean isStillPlaying() {
        return stillPlaying;
    }

    public boolean isSkipOneTurn() {
        return skipOneTurn;
    }

    public void setSkipOneTurn(boolean skipOneTurn) {
        this.skipOneTurn = skipOneTurn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComputer(){
        return computer;
    }
}
