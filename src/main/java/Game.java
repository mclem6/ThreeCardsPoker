
import java.util.ArrayList;





public class Game {

    //
    Player p1;
    Player p2;
    Dealer dealer;
    
    public Game(){
        //initilize players 
        p1 = new Player();
        p2 = new Player();
        dealer = new Dealer();
    }


    //update player ante and pairplus
    void update_p1_bet(int ante, int pairPlus){
        p1.anteBet = ante;
        p1.pairPlusBet = pairPlus;
        p1.bet_set = true;
    }
    
    //update player ante and pairplus
    void update_p2_bet(int ante, int pairPlus){
        p2.anteBet = ante;
        p2.pairPlusBet = pairPlus;
        p2.bet_set = true;
    }

    void dealhands(){

        if(dealer.theDeck.deck.size() < 32){
            dealer.theDeck.newDeck();
        }
        dealer.dealershand = dealer.dealHand();
        p1.hand = dealer.dealHand();
        p2.hand = dealer.dealHand();
    }

    void p1_play_wager_made(){
        //start composing string
        
        //update playBet
        p1.playBet = p1.anteBet;

        //compare the hand against dealer
        int result = ThreeCardLogic.compareHands(dealer.dealershand, p1.hand);




        //evaluate pairplusbet
        if(p1.pairPlusBet != 0){
            p1.totalWinnings += ThreeCardLogic.evalPPWinnings(p1.hand, p1.pairPlusBet);
            

        }


        switch (result) {
            case 0:
                //tied -- set bet and ante plus to zero
                break;
            case 1:
                //dealer won -- player loses antebet and play wager
                p1.totalWinnings -= (p1.anteBet + p1.playBet);
                break;
            default:
                //player won -- player wins antebet and play wager
                p1.totalWinnings += (p1.anteBet + p1.playBet);
                break;
        }
        
    }

  void p2_play_wager_made(){
        //update playBet
        p2.playBet = p2.anteBet;

        //compare the hand against dealer
        int result = ThreeCardLogic.compareHands(dealer.dealershand, p2.hand);
        //evaluate pairplusbet
        p2.totalWinnings += ThreeCardLogic.evalPPWinnings(p2.hand, p2.pairPlusBet);

        switch (result) {
            case 0:
                //tied -- set bet and ante plus to zero
                break;
            case 1:
                //dealer won -- player loses antebet and play wager
                p2.totalWinnings -= (p2.anteBet + p2.playBet);
                break;
            default:
                //player won -- player wins antebet and play wager
                p2.totalWinnings += (p2.anteBet + p2.playBet);
                break;
        }
        
    }

     public String evalHand_str(ArrayList<Card> hand){

        int handVal = ThreeCardLogic.evalHand(hand);

           switch (handVal) {
            case 5:
                return " pair ";
            case 4:
                 return " flush ";
            case 3:
                 return " straight ";
            case 2:
                 return " three of a kind ";
            case 1:
                 return " straight flush ";
            default:
                 return" high Card ";
        }

    }

    public String compareHands_str(Dealer dealer, Player player){
        String message = "";

        message += " hand is " + evalHand_str(player.hand) + " DEALER hand is " + evalHand_str(dealer.dealershand);
        
        int result = ThreeCardLogic.compareHands(dealer.dealershand, player.hand);

        switch (result) {
            case 0:
                message += ". TIE, player loses 0";
                break;
            case 1:
                message += ". DEALER WON, player loses " + String.valueOf(player.anteBet * 2);
                break;
            default:
                message += ". PLAYER WON,  player wins " + String.valueOf(player.anteBet * 2);
        }


        return message;


    }

    public String pp_result_str(Player player){

           switch (ThreeCardLogic.evalHand(player.hand)) {
            case 5:
                return " pairplus hand is pair, player won " + String.valueOf(player.pairPlusBet * 2);
            case 4:
                 return" pairplus hand is flush, player won " + String.valueOf(player.pairPlusBet * 4);
            case 3:
                 return " pairplus hand is straight, player won " + String.valueOf(player.pairPlusBet * 7);
            case 2:
                 return " pairplus hand is three of a kind, player won " + String.valueOf(player.pairPlusBet * 31);
            case 1:
                 return " pairplus hand is straight flush, player won " + String.valueOf(player.pairPlusBet * 41);
            default:
                 return" pairplus hand is high Card, player lost " + String.valueOf(player.pairPlusBet);
        }
    }

    



    
}
