import java.util.ArrayList;
import java.util.Collections;


public class Dealer {
    Deck theDeck;
    ArrayList<Card> dealershand;

    public Dealer(){
        theDeck = new Deck();
    }

    public ArrayList<Card> dealHand(){
        ArrayList<Card> hand = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            Card currCard = theDeck.deck.remove(0);
            hand.add(currCard);
        }


        return hand;
    }


    
}
