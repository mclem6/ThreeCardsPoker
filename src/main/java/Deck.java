import java.util.ArrayList;
import java.util.Collections;


public class Deck extends ArrayList<Card>{

    ArrayList<Card> deck = new ArrayList<>();

    //CONSTRUCTOR
    public Deck(){

        //create cards and add to deck
        for (int i = 2; i <=14; i++){
            deck.add(new Card('C', i));
            deck.add(new Card('D', i));
            deck.add(new Card('S', i));
            deck.add(new Card('H', i));
        }

        //shuffle
        Collections.shuffle(deck);

    }

    //CREATE NEW DECK
    void newDeck(){

        deck.clear(); //delete old deck

        //create new deck
        for (int i = 2; i <=14; i++){
            deck.add(new Card('C', i));
            deck.add(new Card('D', i));
            deck.add(new Card('S', i));
            deck.add(new Card('H', i));
        }

        //shuffle
        Collections.shuffle(deck);

    }


}
