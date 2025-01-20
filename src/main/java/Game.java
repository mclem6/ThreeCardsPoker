

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
        dealer.dealershand = dealer.dealHand();
        p1.hand = dealer.dealHand();
        p2.hand = dealer.dealHand();
    }

    void p1_play_wager_made(){
        //update playBet
        p1.playBet = p1.anteBet;

        //compare the hand against dealer
        int result = ThreeCardLogic.compareHands(dealer.dealershand, p1.hand);
        //evaluate pairplusbet
        p1.totalWinnings += ThreeCardLogic.evalPPWinnings(p1.hand, p1.pairPlusBet);

        switch (result) {
            case 0:
                //tied -- set bet and ante plus to zero
                p1.reset_bet();
                break;
            case 1:
                //dealer won -- player loses antebet and play wager
                p1.totalWinnings -= (p1.anteBet + p1.playBet);
                p1.reset_bet();
                break;
            default:
                //player won -- player wins antebet and play wager
                p1.totalWinnings += (p1.anteBet + p1.playBet);
                p1.reset_bet();
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
                p2.reset_bet();
                break;
            case 1:
                //dealer won -- player loses antebet and play wager
                p2.totalWinnings -= (p2.anteBet + p2.playBet);
                p2.reset_bet();
                break;
            default:
                //player won -- player wins antebet and play wager
                p2.totalWinnings += (p2.anteBet + p2.playBet);
                p2.reset_bet();
                break;
        }
        
    }

    



    
}
