package se.ltu.monopoly;

import se.ltu.Player;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class LTUMonopoly {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private Player[] players;
    private String[] tileNames = new String[] {
            "START", "StiL", "CHANCE", "Philm", "PARTY",
            "A109", "A117", "LIBRARY", "B234Ske", "CHANCE",
            "E632", "EXAM", "A209", "A210"
    };
    private int[][] board = new int[][]
            {
                    //{get, pay, buy, rent, knowledge}
                    {40 , 0  , 0  , 0   , 0}, //START
                    {0  , 0  , 6  , 2   , 0}, //StiL
                    {0  , 0  , 0  , 0   , 0}, //CHANCE
                    {0  , 0  , 6  , 2   , 0}, //Philm
                    {0  , 18 , 0  , 0   , -8},//PARTY
                    {0  , 0  , 10 , 3   , 3}, //A109
                    {0  , 0  , 10 , 3   , 3}, //A117
                    {0  , 0  , 0  , 0   , 8}, //LIBRARY
                    {0  , 0  , 10 , 3   , 3}, //B234Ske
                    {0  , 0  , 0  , 0   , 0}, //CHANCE
                    {0  , 0  , 10 , 3   , 3}, //E632
                    {0  , 0  , 0  , 0   , 0}, //EXAM
                    {0  , 0  , 20 , 5   , 4}, //A209
                    {0  , 0  , 20 , 5   , 4}  //A210
            };
    public Random random = new Random();

    public static void main(String argv[]) {
        if(argv.length < 1 ||argv.length > 4) {
            System.out.println("Syntax: LTUMonopoly [Player 1-4 initials]\nexample: LTUMonopoly F.L J.H K.S");
            System.exit(0);
        } else {
            new LTUMonopoly(argv);
        }
    }

    public LTUMonopoly(String[] playerNames) {
        //There are always 4 players
        ////////////////// must fix here //////////////////////
        players = new Player[4];

        //Fill up with computer players
        int computerID = 1;
        for(int i=0; i<4; i++) {
            if(playerNames.length >= i+1) {
                setPlayer(i, new Player(playerNames[i]));
            } else {
                //C.1 = computer 1, etc.
                setPlayer(i, new Player("C." + computerID, true));
                computerID++;
            }
        }

        //New game is started: show the gameBoard.
        paintGameBoard();
        System.out.println("");
        printInstructions();
        System.out.println("\n");

        int i=0;
        while(true) {
            makeMove(getPlayer(i));
            i++;
            if(i>3) {
                i=0;
            }
        }
    }

    /** Roll dice, move, draw cards, pay rent, or offer to buy property. */
    public void makeMove(Player player) {
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

        if(player.isStillPlaying()) {
            System.out.println("It is now the turn of " + player.getName());

            // Player character - let them press enter before their turn begins.
            if(!player.isComputer()) {
                //Print the game-board for player characters.
                paintGameBoard();
                System.out.println(player.getName() + "Press [enter] to continue. (study-time: "+
                        player.getMoney()+", knowledge: " + player.getKnowledge() + ")");
                try{
                    bufferedReader.readLine();
                } catch(Exception e){};
            }
        } else { // this current player has already lost the game, check if there are any players left playing
            boolean someoneIsStillPlaying = false;
            for(int i=0; i<getPlayersCount(); i++) {
                if(getPlayer(i).isStillPlaying()) {
                    someoneIsStillPlaying = true;
                }
            }
            if(someoneIsStillPlaying) {
                return;
            } else {
                System.out.println("There are no more players, everyone lost");
                System.exit(0);
            }
        }

        // The player is currently on the EXAM tile but had not studied enough (knowledge <= 200) - skip one turn
        // Or, the player is cramming for the exam in the Library (result of a Chance card)
        // Or, the player has passed out after a serious party (result of a Chance card)
        if(player.isSkipOneTurn()) {
            player.setSkipOneTurn(false);
            System.out.println(player.getName() + " skips one turn");
            return;
        }

        int pos = player.getPosition();
        // 1. In the beginning of your turn you may opt to buy unowned property
        // First check if the current tile is unowned - offer player to buy
        if(pos == 1||pos == 3||pos == 5||pos == 6||pos == 8||pos == 10||pos == 12||pos == 13) {
            //board[][get, pay, buy, rent, knowledge]
            if(checkOwned(pos) == null && !player.isComputer()) {
                System.out.print("Do you want to buy " + getTileNames(pos) + " for " + getValueofBoardPosition(pos,2) +
                        " and rent " + getValueofBoardPosition(pos,3) + "? [y/n] \nYou currently have " + player.getMoney() + " study-time\n");
                String choice = "";
                try{
                    while(!"y".equals(choice = bufferedReader.readLine()) && !"n".equals(choice)){
                        System.out.println("Yes [y] or No [n]");
                    }
                } catch (Exception e) {};
                if ("y".equals(choice)) {
                    if(player.getMoney()-getValueofBoardPosition(pos,2) > 0) {
                        player.getOwnsTile().add(Integer.valueOf(pos));
                        player.setMoney(player.getMoney()-getValueofBoardPosition(pos,2));
                        System.out.println(player.getName() + "bought " + getTileNames(pos));
                    }
                } else {System.out.println("You can not afford " + getTileNames(pos));}
            } else if(checkOwned(pos) == null && player.isComputer() && player.getMoney()-getValueofBoardPosition(pos,2) > 0) {
                player.getOwnsTile().add(Integer.valueOf(pos));
                player.setMoney(player.getMoney()-getValueofBoardPosition(pos,2));
                System.out.println(player.getName() + "bought " + getTileNames(pos));
            }
        }

        // 2. Roll d6 dice and move that number of steps.

        int roll = random.nextInt(6) + 1;
        player.setPosition(player.getPosition() + roll);
        if(player.getPosition() > 13) {
            player.setPosition(player.getPosition() - 14);
        }
        if(!player.isComputer()) {
            System.out.println(player.getName() + "rolls a " + roll + " and lands on " + getTileNames(player.getPosition()));
        }

        // 3. If the tile you land on is owned, pay the rent
        Player owner = checkOwned(player.getPosition());
        //board[][get, pay, buy, rent, knowledge]
        if(owner != null) {
            player.setMoney(player.getMoney() - getValueofBoardPosition(player.getPosition(),3));
            owner.setMoney(owner.getMoney() + getValueofBoardPosition(player.getPosition(),3));
            if(player.getMoney() < 0) {
                // exit 1. Lose the game if you are all out of study-time
                System.out.println(player.getName() + " Could not afford to pay the rent and has lost");
                player.setStillPlaying(false);
            } else {
                System.out.println(player.getName() + " paid the rent to " + owner.getName() +
                        " and has " + player.getMoney() + " study-time left");
            }
        }

        // 4. Increase or decrease your knowledge accordingly.
        player.setKnowledge(player.getKnowledge() + getValueofBoardPosition(player.getPosition(),4));

        // 5. Pay any costs involved in arriving to the tile (e.g. PARTY)
        player.setMoney(player.getMoney() - getValueofBoardPosition(player.getPosition(),1));
        if(player.getMoney() < 0) {
            // exit 1. Lose the game if you are all out of study-time
            System.out.println(player.getName() + " Could not afford to pay for the party and has lost");
            player.setStillPlaying(false);
        }

        // exit 2. Win the game if you have >= 200 knowledge and stand at EXAM
        if(player.getPosition() == 11) {
            if (player.getKnowledge() >= 200) {
                System.out.println(player.getName() + " PASSED THE EXAM AND WINS THE GAME! CONGRATULATIONS!");
                System.exit(0);
            } else {
                System.out.println(player.getName() + " had not studied enough for the exam and have to take a re-exam. Skip one turn");
                player.setSkipOneTurn(true);
            }
        }

        // 6. Draw a Chance card on the CHANCE tile and follow the text
        if(player.getPosition() == 2 || player.getPosition() == 9) {
            drawChanceCard(player);
        }

        System.out.println(player.getName() + " has " + player.getMoney() + " study-time and " + player.getKnowledge() + " knowledge");
    }

    public Player checkOwned(int pos) {
        for(int i=0; i< getPlayersCount(); i++) {
            if(getPlayer(i).getOwnsTile().contains(Integer.valueOf(pos))) {
                return getPlayer(i);
            }
        }
        return null;
    }

    public void drawChanceCard(Player player) {
        int card = random.nextInt(5);
        //board[][get, pay, buy, rent, knowledge]
        switch(card) {
            case 0:	System.out.println(player.getName() + "has decided to cram for the exam in the LIBRARY this turn and the next");
                player.setKnowledge(player.getKnowledge() + 32);
                player.setPosition(7);
                player.setSkipOneTurn(true);
                break;
            case 1:	System.out.println(player.getName() + "has fallen ill. Go to START without collecting any study-time");
                player.setPosition(0);
                break;
            case 2: System.out.println(player.getName() + "has been given a VERBAL EXAM and moves to EXAM without losing a turn");
                player.setPosition(11);
                if(player.getKnowledge() >= 200) {
                    System.out.println(player.getName() + " PASSED THE EXAM AND WINS THE GAME! CONGRATULATIONS!");
                    System.exit(0);
                }
                break;
            case 3: System.out.println(player.getName() + "\"PWNZ\" at the workshops.\n" +
                    player.getName() + "gains ownership of A209 and A210, moves to A209 and collects the knowledge");
                Player owner;
                owner = checkOwned(12);
                if(owner != null) {
                    owner.getOwnsTile().remove(Integer.valueOf(12));
                }
                owner = checkOwned(13);
                if(owner != null) {
                    owner.getOwnsTile().remove(Integer.valueOf(13));
                }
                player.getOwnsTile().add(Integer.valueOf(12));
                player.getOwnsTile().add(Integer.valueOf(13));
                player.setPosition(12);
                player.setKnowledge(player.getKnowledge() + getValueofBoardPosition(player.getPosition(),4));
                break;
            case 4: System.out.println(player.getName() + "has passed out after a serious party.\n"+
                    player.getName() + "moves to PARTY, pays the costs, decreases knowledge, and skips one turn");
                player.setPosition(4);
                player.setSkipOneTurn(true);
                player.setKnowledge(player.getKnowledge() + getValueofBoardPosition(player.getPosition(),4));
                player.setMoney(player.getMoney() - getValueofBoardPosition(player.getPosition(),1));
                if(player.getMoney() < 0) {
                    // exit 1. Lose the game if you are all out of study-time
                    System.out.println(player.getName() + " Could not afford to pay for the party and has lost");
                    player.setStillPlaying(false);
                }
                break;
        }
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

    public Player getPlayer(int player) {
        return players[player];
    }

    public int getPlayersCount() {
        return players.length;
    }

    public void setPlayer(int pos, Player mPlayer) {
        this.players[pos] = mPlayer;
    }

    public String getTileNames(int position) {
        return tileNames[position];
    }

    public void setTileNames(String[] tileNames) {
        this.tileNames = tileNames;
    }

    public int getValueofBoardPosition(int x, int y) {
        return board[x][y];
    }
}