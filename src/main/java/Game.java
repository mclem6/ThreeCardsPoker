
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
    void update_bet(Player player, int ante, int pairPlus){
       player.anteBet = ante;
       player.pairPlusBet = pairPlus;
       player.bet_set = true;
    }
    

    void dealhands(){

        if(dealer.theDeck.deck.size() < 32){
            dealer.theDeck.newDeck();
        }
        dealer.dealershand = dealer.dealHand();
        p1.hand = dealer.dealHand();
        p2.hand = dealer.dealHand();
    }

    void player_fold(Player player){

        //player forfeits ante and pairplus bet
        player.totalWinnings -= (player.anteBet + player.pairPlusBet);
    }

    void play_wager_made(Player player){
        
        //update playBet
        player.playBet = player.anteBet;

        //compare the hand against dealer
        int result = ThreeCardLogic.compareHands(dealer.dealershand, player.hand);


        //evaluate pairplusbet
        if(player.pairPlusBet != 0){
            player.totalWinnings += ThreeCardLogic.evalPPWinnings(player.hand, player.pairPlusBet);
            

        }


        switch (result) {
            case 0:
                //tied -- set bet and ante plus to zero
                break;
            case 1:
                //dealer won -- player loses antebet and play wager
               player.totalWinnings -= (player.anteBet + player.playBet);
                break;
            default:
                //player won -- player wins antebet and play wager
                player.totalWinnings += (player.anteBet + player.playBet);
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
                return " pairplus hand is pair, player wins " + String.valueOf(player.pairPlusBet * 2);
            case 4:
                 return" pairplus hand is flush, player wins " + String.valueOf(player.pairPlusBet * 4);
            case 3:
                 return " pairplus hand is straight, player wins " + String.valueOf(player.pairPlusBet * 7);
            case 2:
                 return " pairplus hand is three of a kind, player wins " + String.valueOf(player.pairPlusBet * 31);
            case 1:
                 return " pairplus hand is straight flush, player wins " + String.valueOf(player.pairPlusBet * 41);
            default:
                 return" pairplus hand is high Card, player loses " + String.valueOf(player.pairPlusBet);
        }
    }
    
}
