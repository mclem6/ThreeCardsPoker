import java.util.ArrayList;

public class Player {

    ArrayList<Card> hand;
    int anteBet;
    int playBet;
    int pairPlusBet;
    int totalWinnings;
    Boolean bet_set;

    public Player(){
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
        totalWinnings = 0;
        bet_set = false;
    }

    public void reset_bet(){
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
    }
    
}
