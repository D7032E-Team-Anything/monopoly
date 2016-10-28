package se.ltu.monopoly;

import se.ltu.monopoly.Tiles.Tile;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Board extends Thread{
    private Dice mDice;
    private ArrayList<NewPlayer> mPlayers;
    private ArrayList<Tile> mTiles;
    private boolean running;
    public void setRunning(boolean running){
        this.running = running;
    }
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public boolean someoneIsStillPlaying(){
        for(int i=0; i<getPlayersCount(); i++) {
            if(getPlayer(i).isStillPlaying()) {
                return true;
            }
        }
        return false;
    }
    public Dice getmDice() {
        return mDice;
    }

    public void setmDice(Dice mDice) { this.mDice = mDice; }

    public ArrayList<NewPlayer> getmPlayers() {
        return mPlayers;
    }

    public void setmPlayers(ArrayList<NewPlayer> mPlayers) {
        this.mPlayers = mPlayers;
    }

    public ArrayList<Tile> getmTiles() {
        return mTiles;
    }

    public void setmTiles(ArrayList<Tile> mTiles) {
        this.mTiles = mTiles;
    }

    public void printInstructions() {
        System.out.println("Currency: Study-time (Time is money, start with 200)");
        System.out.println("Tiles:");
        System.out.println("\tSTART: Collect 40");
        System.out.println("\tStiL/Philm: Go to the gym/cinema [buy: 6, rent 2]");
        System.out.println("\tCHANCE: Draw a CHANCE card");
        System.out.println("\tPARTY: Have a huge party [pay: 18, Decrease knowledge by 8]");
        System.out.println("\tA109/A117/B234Ske/E632: Attend a lecture [buy: 10, rent 3, Increase knowledge by 3]");
        System.out.println("\tLIBRARY: Study [Increase knowledge by 8]");
        System.out.println("\tEXAM: Win if knowledge >=200 / Skip one turn.");
        System.out.println("\tA209/A210: Attend a workshop [buy: 20, rent 5, Increase knowledge by 4]");
        System.out.println("Win by collecting 200 knowlede and go to the EXAM tile. Lose by running out of study-time");
    }

    public NewPlayer getPlayer(int player) {
        return mPlayers.get(player);
    }

    public int getPlayersCount() {
        return mPlayers.size();
    }

    /** Draw a text-representation of the game-board. */
    public void paintGameBoard() {
/*
		***************************************************
		*  START  *  StiL   * CHANCE  *  Philm  *  PARTY  *
		*         *         *         *         *         *
		*    0    *    1    *    2    *    3    *    4    *
		*         *         *         *         *         *
		*         *         *         *         *         *
		***************************************************
		*  A210   *                             *  A109   *
		*         *                             *         *
		*   13    *                             *    5    *
		*         *                             *         *
		*         *                             *         *
		***********                             ***********
		*  A209   *                             *  A117   *
		*         *                             *         *
		*   12    *                             *    6    *
		*         *                             *         *
		*         *                             *         *
		***************************************************
		*  EXAM   *  E632   * CHANCE  * B234Ske * LIBRARY *
		*         *         *         *         *         *
		*   11    *   10    *    9    *    8    *    7    *
		*         *         *         *         *         *
		*         *         *         *         *         *
		***************************************************

		Currency: Study-time (Time is money)
		START: Collect 40
		StiL/Philm: Go to the gym/cinema [buy: 6, rent 2]
		CHANCE: Draw a CHANCE card
		PARTY: Have a huge party [pay: 18, Decrease knowledge by 8]
		A109/A117/B234Ske/E632: Attend a lecture [buy: 10, rent 3, Increase knowledge by 3]
		LIBRARY: Study [Increase knowledge by 8]
		EXAM: Win if knowledge >=200 / Skip one turn
		A209/A210: Attend a workshop [buy: 20, rent 5, Increase knowledge by 4]
*/

        String[][] boardTiles = new String[16][];
        boardTiles[0] = new String[] {"***********", "*  START  *", "*         *", "***********"};
        boardTiles[1] = new String[] {"**********", "  StiL   *", "         *", "**********"};
        boardTiles[2] = new String[] {"**********", " CHANCE  *", "         *", "**********"};
        boardTiles[3] = new String[] {"**********", "  Philm  *", "         *", "**********"};
        boardTiles[4] = new String[] {"**********", "  PARTY  *", "         *", "**********"};

        boardTiles[13] = new String[] {"", "*  A210   *", "*         *", "***********"}; //6 line tile
        //Add 3 empty tiles (boardTiles[14/15]) in between while printing it on the screen
        boardTiles[5] = new String[] {"", "  A109   *", "         *", "**********"}; //6 line tile

        boardTiles[12] = new String[] {"", "*  A209   *", "*         *", ""}; //5 line tile
        //Add 3 empty tiles (boardTiles[14/15]) in between while printing it on the screen
        boardTiles[6] = new String[] {"", "  A117   *", "         *", ""}; //5 line tile

        boardTiles[11] = new String[] {"***********", "*  EXAM   *", "*         *", "***********"};
        boardTiles[10] = new String[] {"**********", "  E632   *", "         *", "**********"};
        boardTiles[9] = new String[] {"**********", " CHANCE  *", "         *", "**********"};
        boardTiles[8] = new String[] {"**********", " B234Ske *", "         *", "**********"};
        boardTiles[7] = new String[] {"**********", " LIBRARY *", "         *", "**********"};

        //Empty board tiles
        boardTiles[14] = new String[] {"          ", "          ", "          ", "          "};
        boardTiles[15] = new String[] {"         *", "         *", "         *", "         *"};

        int[] printorder = new int[] {0, 1, 2, 3, 4, 13, 14, 14, 15, 5, 12, 14, 14, 15, 6, 11, 10, 9, 8, 7};

        for(int i=0; i<printorder.length; i=i+5) { //there are 5 tiles in each row
            for(int line=0; line<7; line++) { //each tile consists of 5 to 7 lines
                for(int tile=0; tile<5; tile++) { //print all 5 tiles in the row
                    if(line==0 && "".equals(boardTiles[printorder[i+tile]][0])) {
                        line++; //Don't add the first line for tile 13, 5, 12, 6
                    }
                    if(line==6 && "".equals(boardTiles[printorder[i+tile]][3])) {
                        break; //Don't add the last line for tile 12, 6
                    }

                    //Print tiles in the right order
                    if(line < 2) { //print the stars and the tile name
                        System.out.print(boardTiles[printorder[i+tile]][line]);
                    } else if(line < 6) { //Print the lines allocated to players
                        if(getPlayer(line-2).getPosition() == printorder[i+tile]) {
                            // player 1-4 is located at the current tile being printed
                            int stars = boardTiles[printorder[i+tile]][2].replaceAll("\\s+","").length();
                            if(stars==1) {
                                System.out.print(getPlayer(line-2).getName() + "*");
                            } else {
                                System.out.print("*" + getPlayer(line-2).getName() + "*");
                            }
                        } else { //No player is located at this tile
                            System.out.print(boardTiles[printorder[i+tile]][2]);
                        }
                    } else {
                        System.out.print(boardTiles[printorder[i+tile]][3]);
                    }

                    if(((i+tile+1) % 5) == 0) { //create a newline after every 5th tile
                        System.out.println("");
                    }
                }
            }
        }
    }

    private String readPlayerChoice(){
        String choice = "";
        try {
            while (!"y".equals(choice = bufferedReader.readLine()) && !"n".equals(choice)) {
                System.out.println("Yes [y] or No [n]");
            }
        } catch (Exception e) {
        }
        return choice;
    }
    public void offerToBuy(NewPlayer player) {
        int pos = player.getPosition();
        Tile tile = mTiles.get(pos);
            //board[][get, pay, buy, rent, knowledge]
            if (tile.isOwnable() && tile.getOwner() == -1) {
                String choice;
                if(player.isComputer()){
                    choice = "y";
                }else {
                    System.out.print("Do you want to buy " + tile.getName() +
                            " for " + tile.getBuyCoast() +
                            " and rent " + tile.getRentCoast() +
                            "? [y/n] \nYou currently have " + player.getMoney() +
                            " study-time\n");
                    choice = readPlayerChoice();
                }
                if ("y".equals(choice)) {
                    if (player.getMoney() - tile.getBuyCoast() > 0) {
                        tile.setOwner(player.getPosition());
                        player.setMoney(player.getMoney() - tile.getBuyCoast());
                        System.out.println(player.getName() + "bought " + tile.getName());
                    }
                } else {
                    System.out.println("You can not afford " + tile.getName());
                }
            }
    }
    public boolean playerCanAffordMoveCost(NewPlayer player){
        Tile tile = mTiles.get(player.getPosition());
        if(player.getMoney() < tile.getRentCoast()){
            System.out.println(player.getName() + " Could not afford to pay the rent and has lost");
            player.setStillPlaying(false);
            return false;
        }
        return true;
    }
    public void makeMove(NewPlayer player){
        Tile tile = mTiles.get(player.getPosition());
        tile.stepInside(player, this);
        afterMove(player);
    }

    public void afterMove(NewPlayer player){

    }

    public void run() {
        //New game is started: show the gameBoard.
        paintGameBoard();
        System.out.println("");
        printInstructions();
        System.out.println("\n");

        int i=0;
        while(running){
            NewPlayer player = getPlayer(i);
            if(player.isSkipOneTurn()){
                skipPlayer(player);
                return;
            }

            offerToBuy(player); // This should be called each time
            playerMove(player);

            }
            i++;
            if(i>3) {
                i=0;
            }
    }
    public void playerMove(NewPlayer player){
        rollDice(player);
        if(playerCanAffordMoveCost(player)){
            Tile tile = mTiles.get(player.getPosition());
            tile.stepInside(player, this);
        }
    }
    private void rollDice(NewPlayer player){
        int roll = mDice.roll();
        player.setPosition(player.getPosition() + roll);
        if(player.getPosition() > mTiles.size() -1){
            player.setPosition(player.getPosition() - mTiles.size());
        }
    }
    private void skipPlayer(NewPlayer player){
        if(player.isSkipOneTurn()) {
            player.setSkipOneTurn(false);
            System.out.println(player.getName() + " skips one turn");
        }
    }
}
