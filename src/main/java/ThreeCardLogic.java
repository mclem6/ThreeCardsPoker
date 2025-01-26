import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic{



    //0 high card
    //1 straight flush
    //2 three of a kind
    //3 straight
    //4 flush
    //5 pair
    //returns integer value of hand passed in
    public static int evalHand(ArrayList<Card> hand){
        ArrayList<Integer> value = new ArrayList<>();
        ArrayList<Character> suit = new ArrayList<>();
        Boolean is_flush= false;
        Boolean is_straight = false;

        for (int i = 0; i < hand.size(); i++){
            value.add(hand.get(i).value);
            suit.add(hand.get(i).suit);
        }

        //check if straight 
       Collections.sort(value);
       if(((value.get(0) + 1) == value.get(1)) & ((value.get(1) + 1) == value.get(2))){
            is_straight = true; 
       }

       //check if flush
       if((suit.get(0) == suit.get(1)) & (suit.get(0) == suit.get(2))){
                is_flush = true;

       }

       //check if straight flush
       if(is_flush & is_straight){  
            return 1;
       } 

       //check if 3 of kind
       if((value.get(0) == value.get(1)) & (value.get(0) == value.get(2))){
            return 2;
       }

       //check if straight
       if(is_straight){
            return 3;
       }

       //check if flush
       if(is_flush){
            return 4;
       }
        
       //check if  pair
       if((value.get(0) == value.get(1)) | (value.get(0) == value.get(2)) | (value.get(1) == value.get(2))){
            return 5;
       }

       return 0;

    };



    //return amount won for the PairPlus bet 
    public static int evalPPWinnings(ArrayList<Card> hand, int bet){
        int handVal = evalHand(hand);

        switch (handVal) {
            case 5:
                return bet * 2;
            case 4:
                return bet * 4;
            case 3:
                return bet * 7;
            case 2:
                return bet * 31;
            case 1:
                return bet * 41;
            default:    
                return (-1 * bet);
        }

    }



    //
    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player){
        if(evalHand(dealer) == evalHand(player)){
            return 0;
        } else if (evalHand(dealer) == 0){
            return 2;
        } else if (evalHand(player) == 0){
            return 1;
        } else if (evalHand(dealer) < evalHand(player)){
            return 1;
        }

        return 2;
    }





}

    




