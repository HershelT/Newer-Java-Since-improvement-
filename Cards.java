import java.util.*;
import java.lang.*;
class Cards {
    private ArrayList<cardType> deck = new ArrayList<cardType>();
    private ArrayList<cardType> playerDeck = new ArrayList<cardType>();
    private ArrayList<cardType> CPUDeck = new ArrayList<cardType>();
    private String[] typeOfCards = {"Hearts", "Spades", "Clubs", "Diamonds"};

    public Cards(){
        for (int x = 0; x < 4; x ++) {
            for (int i = 2; i < 15; i++) {
                cardType newCard = new cardType(i, typeOfCards[x]);
                deck.add(newCard);
            }
        }
    }
    public ArrayList<cardType> shuffleDeck() {
        for (int c = 0; c < 5; c++)
            Collections.shuffle(deck);
        return deck;
    }
    public void splitDecks(){
        for (int j = 0; j < deck.size()/2; j++) {
            playerDeck.add(deck.get(j));
            CPUDeck.add(deck.get(deck.size() - j - 1));
        }
    }
    public ArrayList<cardType> getDeck() {
        return deck;
    }
    public ArrayList<cardType> getPlayerDeck(){
        return playerDeck;
    }
    public ArrayList<cardType> getCPUDeck() {
        return CPUDeck;
    }

    public static void main(String[] args) {
        String answer = "";
        int highestTurnCount = 0;
        int lowestTurnCount = Integer.MAX_VALUE;
        do {
            newGame game = new newGame();
            int testCount = game.playGame();
            if (testCount > highestTurnCount) {
                highestTurnCount = testCount;
            }
            if (testCount < lowestTurnCount) {
                lowestTurnCount = testCount;
            }
            Scanner myPick = new Scanner(System.in);
            System.out.println("D0 you want to play again? (y(or 'enter') or n)");
            answer = myPick.nextLine();
        } while (!answer.equals("n"));
        System.out.println("Ending games and closing down");
        System.out.println("The Largest Game with the most amount of turns was " + highestTurnCount);
        System.out.println("The Smallest Game with the least amount of turns was " + lowestTurnCount);

    }
}
class cardType {
    private int cardNum;
    private String cardType;
    public cardType(int cardNum, String cardType) {
        this.cardNum = cardNum;
        this.cardType = cardType;
    }
    public int getCardNum() {
        return cardNum;
    }
    public String getCardType(){
        return cardType;
    }
}

class newGame{
    private ArrayList<cardType> playerDeck;
    private ArrayList<cardType> CPUDeck;
    private int turnCount = 0;
    public newGame(){
        Cards newDeck = new Cards();
        newDeck.shuffleDeck();
        newDeck.splitDecks();
        playerDeck = newDeck.getPlayerDeck();
        CPUDeck = newDeck.getCPUDeck();
    }
    public void sleep(int time){
        try{
            Thread.sleep(time);
        } catch (Exception E) {
            System.out.println("False");
        }
    }

    public int playGame(){
        System.out.println("Playing War-The Notorious Card Game");
        ArrayList<cardType> tieBreakers = new ArrayList<cardType>();
        while (true)
        {
            System.out.println("Player Card " + playerDeck.get(0).getCardNum() + " of " + playerDeck.get(0).getCardType());
            System.out.println("CPU Card " + CPUDeck.get(0).getCardNum()+ " of " +  CPUDeck.get(0).getCardType());
            //sleep(3000);
            if (playerDeck.get(0).getCardNum() > CPUDeck.get(0).getCardNum()) {
                playerDeck.add(CPUDeck.get(0));
                playerDeck.add(playerDeck.get(0));
                playerDeck.remove(0);
                CPUDeck.remove(0);
                System.out.println("-------------------\nPlayer beats CPU");
                if (tieBreakers.size() > 0) {
                    playerDeck.addAll(tieBreakers);
                    System.out.println("And the Player wins the tie breaker!");
                    tieBreakers = new ArrayList<cardType>();
                }
                System.out.println("--------------------");
            }
            else if (playerDeck.get(0).getCardNum() < CPUDeck.get(0).getCardNum()) {
                CPUDeck.add(playerDeck.get(0));
                CPUDeck.add(CPUDeck.get(0));
                playerDeck.remove(0);
                CPUDeck.remove(0);
                System.out.println("--------------------\nCPU sadly beats Player");
                if (tieBreakers.size() > 0) {
                    CPUDeck.addAll(tieBreakers);
                    System.out.println("And the CPU Crushes the tie breaker!");
                    tieBreakers = new ArrayList<cardType>();
                }
                System.out.println("--------------------");
            }
            else {
                System.out.println("IT'S a TIE!!!!!\n--------------------");
                for (int i = 0; i < 4; i++) {
                    if (playerDeck.size() > 1){
                        tieBreakers.add(playerDeck.get(0));
                        playerDeck.remove(0);
                    }
                    if (CPUDeck.size() > 1) {
                        tieBreakers.add(CPUDeck.get(0));
                        CPUDeck.remove(0);
                    }
                }
            }
            turnCount++;
            if (playerDeck.size() <= 0) {
                System.out.println("Game Over: CPU WINS");
                System.out.println(CPUDeck.size());
                break;
            }
            else if (CPUDeck.size() <= 0) {
                System.out.println("Game Over: PLAYER WINS");
                System.out.println(playerDeck.size());
                break;
            }
            //sleep(1500);
        }
        System.out.println("End of The Game (Turn Count - " + turnCount + ")");
        return turnCount;
    }
}


