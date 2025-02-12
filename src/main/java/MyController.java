import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


public class MyController implements Initializable{

	@FXML
	Button bet1; 

	@FXML
	Button bet2; 


	@FXML
	Button playWager1;

	@FXML
	Button playWager2;

	@FXML
	Button fold1;
	
	@FXML
	Button fold2;

	@FXML
	Button incButtonAnte1;

	@FXML
	Button incButtonAnte2;

	@FXML
	Button decButtonAnte1;

	@FXML
	Button decButtonAnte2;

	@FXML
	Button incButtonPP1;

	@FXML
	Button incButtonPP2;

	@FXML
	Button decButtonPP1;

	@FXML
	Button decButtonPP2;


	@FXML
	TextField player1Ante;

	@FXML
	TextField player2Ante;

	@FXML 
	TextField player1PairPlus;
	
	@FXML 
	TextField player2PairPlus; 

	@FXML
	BorderPane root1;

	@FXML
	BorderPane root2;

	@FXML
	ListView<String> game_info;

	@FXML
	ImageView dealercard1;

	@FXML
	ImageView dealercard2;

	@FXML
	ImageView dealercard3;

	@FXML
	ImageView player1card1;

	@FXML
	ImageView player1card2;

	@FXML
	ImageView player1card3;

	@FXML
	ImageView player2card1;

	@FXML
	ImageView player2card2;

	@FXML
	ImageView player2card3;

	@FXML 
	Text p1bank;

	@FXML 
	Text p2bank;

	@FXML
	Button play_again_button;

	

	Game game;



    @Override
	public void initialize(URL location, ResourceBundle resources) {


	}

	public void update_UI_result(){


			//check if player 1 folded
			if(game.p1.playBet == 0){
				game_info.getItems().add("PLAYER 1 folded and forfeited ante and pairplus bet of " + String.valueOf(game.p1.anteBet + game.p1.pairPlusBet) + ". Player 1 loses " + String.valueOf(game.p2.anteBet + game.p2.pairPlusBet));

			} else{
				//show cards
				update_p1_card_UI();

				//updatelistview
				game_info.getItems().add("PLAYER 1" + game.compareHands_str(game.dealer, game.p1));
				game_info.getItems().add("Player 1 " + game.pp_result_str(game.p1));

			}

			//check if player 2 folded
			if(game.p2.playBet == 0){
				game_info.getItems().add("PLAYER 2 folded and forfeited ante and pairplus bet of " + String.valueOf(game.p2.anteBet + game.p2.pairPlusBet) + ". Player 2 loses " + String.valueOf(game.p2.anteBet + game.p2.pairPlusBet) );

			} else{
				//show cards
				update_p2_card_UI();

				//updatelistview
				game_info.getItems().add("PLAYER 2" + game.compareHands_str(game.dealer, game.p2));
				game_info.getItems().add("Player 2 " + game.pp_result_str(game.p2));

			}
	

			//update banks
			p1bank.setText(String.valueOf(game.p1.totalWinnings));
			p2bank.setText(String.valueOf(game.p2.totalWinnings));
		

			//wait 3 seconds

			game_info.getItems().add("Press Next Round to continue");

			//reset game UI
			play_again_button.setDisable(false);





	}

	public void bet_set(){
		//display next step
			game_info.getItems().add("press PLAY WAGER to wage your ante amount  \nOR \npress FOLD and forfeit ante + pairplus bet made");

			//enable buttons for both players
			playWager1.setDisable(false);
			fold1.setDisable(false);
			playWager2.setDisable(false);
			fold2.setDisable(false);

			//deal cards
			game.dealhands();

			//update UI cards
			update_dealer_card_UI();
			
	}

	public void update_dealer_card_UI(){
		dealercard1.setImage(new Image("/images/cards/" + game.dealer.dealershand.get(0).suit + String.valueOf(game.dealer.dealershand.get(0).value) + ".png"));
		dealercard2.setImage(new Image("/images/cards/" + game.dealer.dealershand.get(1).suit + String.valueOf(game.dealer.dealershand.get(1).value) + ".png"));
		dealercard3.setImage(new Image("/images/cards/" + game.dealer.dealershand.get(2).suit + String.valueOf(game.dealer.dealershand.get(2).value) + ".png"));
	
	}
		
	public void update_p1_card_UI(){
		player1card1.setImage(new Image("/images/cards/" + game.p1.hand.get(0).suit + String.valueOf(game.p1.hand.get(0).value) + ".png"));
		player1card2.setImage(new Image("/images/cards/" + game.p1.hand.get(1).suit + String.valueOf(game.p1.hand.get(1).value) + ".png"));
		player1card3.setImage(new Image("/images/cards/" + game.p1.hand.get(2).suit + String.valueOf(game.p1.hand.get(2).value) + ".png"));
		
	}

	public void update_p2_card_UI(){
		player2card1.setImage(new Image("/images/cards/" + game.p2.hand.get(0).suit + String.valueOf(game.p2.hand.get(0).value) + ".png"));
		player2card2.setImage(new Image("/images/cards/" + game.p2.hand.get(1).suit + String.valueOf(game.p2.hand.get(1).value) + ".png"));
		player2card3.setImage(new Image("/images/cards/" + game.p2.hand.get(2).suit + String.valueOf(game.p2.hand.get(2).value) + ".png"));
		
	}

	public void reset_card_UI(){
		dealercard1.setImage(new Image("/images/cardBack.png"));
		dealercard2.setImage(new Image("/images/cardBack.png"));
		dealercard3.setImage(new Image("/images/cardBack.png"));

		player1card1.setImage(new Image("/images/cardBack.png"));
		player1card2.setImage(new Image("/images/cardBack.png"));
		player1card3.setImage(new Image("/images/cardBack.png"));

		player2card1.setImage(new Image("/images/cardBack.png"));
		player2card2.setImage(new Image("/images/cardBack.png"));
		player2card3.setImage(new Image("/images/cardBack.png"));
		

	}

	//press play button action
	public void pressPlay(ActionEvent e)throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/playScene.fxml"));
		Parent root2 = loader.load();
		MyController myctr = loader.getController();
		root2.getStylesheets().add("/styles/stylePlay.css");
		root1.getScene().setRoot(root2);

		//initiliaze game
		myctr.game = new Game();

		myctr.game_info.getItems().add("game starting, select ante and pairplus the press BET");



	}	

	public void pressExit(ActionEvent e)throws IOException{
		Platform.exit();

	}	

	public void new_look(){

		BackgroundImage myBI= new BackgroundImage(new Image("/images/blue_background.jpg", 1250, 1500, false, false),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
		//then you set to your node
		root2.setBackground(new Background(myBI));
	
	}

	public void reset(){
		//reset p1 buttons disable
		bet1.setDisable(false);
		playWager1.setDisable(true);
		fold1.setDisable(true);
		incButtonAnte1.setDisable(false);
		decButtonAnte1.setDisable(false);
		incButtonPP1.setDisable(false);
		decButtonPP1.setDisable(false);
		player1Ante.setDisable(false);
		player1PairPlus.setDisable(false);

		//reset p2 buttons disable
		bet2.setDisable(false);
		playWager2.setDisable(true);
		fold2.setDisable(true);
		incButtonAnte2.setDisable(false);
		decButtonAnte2.setDisable(false);
		incButtonPP2.setDisable(false);
		decButtonPP2.setDisable(false);
		player2Ante.setDisable(false);
		player2PairPlus.setDisable(false);

		//disbale play again button
		play_again_button.setDisable(true);

		//reset players bets
		game.p1.reset_bet();
		game.p2.reset_bet();

		//reset cards UI
		reset_card_UI();

		//reset bet made
		game.p1.bet_set= false;
		game.p2.bet_set= false;


		//update listview
		game_info.getItems().add("game starting!\nSelect ante and pairplus then press BET");
	}


	// -------- PLAYER 1 ------------
	public void player1Bet(ActionEvent e)throws IOException{

		//update 
		game.update_bet(game.p1, Integer.valueOf(player1Ante.getText()), Integer.valueOf(player1PairPlus.getText()));
		

		//disable bet buttons
		bet1.setDisable(true);
		incButtonAnte1.setDisable(true);
		decButtonAnte1.setDisable(true);
		incButtonPP1.setDisable(true);
		decButtonPP1.setDisable(true);

		//disable textfields
		player1Ante.setDisable(true);
		player1PairPlus.setDisable(true);
	

		//update listview
		game_info.getItems().add("player 1 bet ante: " + game.p1.anteBet + " pairplus: " + game.p1.pairPlusBet);

		//check if player 2 made bet
		if(game.p2.bet_set){
			bet_set();
			game.p1.bet_set = false;
			game.p2.bet_set = false;
		} else{
			game_info.getItems().add("waiting for player 2...");
		}
	}

	public void playwager1pressed(ActionEvent e)throws IOException{

		//disable player wager and fold button
		playWager1.setDisable(true);
		fold1.setDisable(true);

		//update p1's bet_set
		game.p1.bet_set = true;

		//update player's info
		game.play_wager_made(game.p1);
	

		//check to see if p2 has selected
		if(game.p2.bet_set){
			
			update_UI_result();
		} else{
			
			game_info.getItems().add("waiting for player 2...");

		}

	}

	public void player1fold(){
		
		//disable button
		fold1.setDisable(true);

		//call player_fold to update totalwinning
		game.player_fold(game.p1);

		//set bet to true
		game.p1.bet_set = true;

		//check if p2 has bet_set
		if(game.p2.bet_set){
			update_UI_result();
		}else {
			game_info.getItems().add("waiting for player 2...");
		}

	}

	public void ante1Inc(ActionEvent e)throws IOException{
		//check if less than 25, if yes increase by 5
		if(Integer.valueOf(player1Ante.getText()) < 25){
			//increment by 5
			player1Ante.setText(String.valueOf(Integer.valueOf(player1Ante.getText()) + 5));
		}
	}

	public void ante1Dec(ActionEvent e)throws IOException{
		//check if greater than 0
		if(Integer.valueOf(player1Ante.getText()) > 0){
			//decrement by 5
			player1Ante.setText(String.valueOf(Integer.valueOf(player1Ante.getText()) - 5));
		}

	}

	public void pairPlus1Inc(ActionEvent e)throws IOException{

		//check if less than 25, if yes increase by 5
		if(Integer.valueOf(player1PairPlus.getText()) < 25){
			//increment by 5
			player1PairPlus.setText(String.valueOf(Integer.valueOf(player1PairPlus.getText()) + 5));
		}

	}

	public void pairPlus1Dec(ActionEvent e)throws IOException{
		//check if greater than 0
		if(Integer.valueOf(player1PairPlus.getText()) > 0){
			//decrement by 5
			player1PairPlus.setText(String.valueOf(Integer.valueOf(player1PairPlus.getText()) - 5));
		}

	}

	// -------- PLAYER 2 ------------
	public void player2Bet(ActionEvent e)throws IOException{

		//update 
		game.update_bet(game.p2, Integer.valueOf(player2Ante.getText()), Integer.valueOf(player2PairPlus.getText()));

		//disable bet buttons
		bet2.setDisable(true);
		incButtonAnte2.setDisable(true);
		decButtonAnte2.setDisable(true);
		incButtonPP2.setDisable(true);
		decButtonPP2.setDisable(true);

		//disable textfields
		player2Ante.setDisable(true);
		player2PairPlus.setDisable(true);
	

		//update listview
		game_info.getItems().add("player 2 bet ante: " + game.p2.anteBet + " pairplus: " + game.p2.pairPlusBet);

		//check if player 1 made bet
		if(game.p1.bet_set){
			bet_set();		
			game.p1.bet_set = false;
			game.p2.bet_set = false;
			
		} else{
			game_info.getItems().add("waiting for player 1...");
		}
	}

	public void playwager2pressed(ActionEvent e)throws IOException{

		//disable player wager and fold button
		playWager2.setDisable(true);
		fold2.setDisable(true);

		//update p2's bet_set
		game.p2.bet_set = true;

		//update player's info
		game.play_wager_made(game.p2);

		//check to see if p1 has selected
		if(game.p1.bet_set){

			update_UI_result();
		} else{
			
			game_info.getItems().add("waiting for player 1...");

		}

	}

	public void player2fold(){

		//disable button
		fold2.setDisable(true);

		//call player_fold to update totalwinning
		game.player_fold(game.p2);

		//set bet to true
		game.p2.bet_set = true;

		//check if p2 has bet_set
		if(game.p1.bet_set){
			update_UI_result();
		}else {
			game_info.getItems().add("waiting for player 1...");
		}

	}

	public void ante2Inc(ActionEvent e)throws IOException{

		if(Integer.valueOf(player2Ante.getText()) < 25){
			//increment by 5
			player2Ante.setText(String.valueOf(Integer.valueOf(player2Ante.getText()) + 5));
		}

	}

	public void ante2Dec(ActionEvent e)throws IOException{
		//check if greater than 0
		if(Integer.valueOf(player2Ante.getText()) > 0){
			//decrement by 5
			player2Ante.setText(String.valueOf(Integer.valueOf(player2Ante.getText()) - 5));
		}

	}

	public void pairPlus2Inc(ActionEvent e)throws IOException{

		//check if less than 25, if yes increase by 5
		if(Integer.valueOf(player2PairPlus.getText()) < 25){
			//increment by 5
			player2PairPlus.setText(String.valueOf(Integer.valueOf(player2PairPlus.getText()) + 5));
		}
	}

	public void pairPlus2Dec(ActionEvent e)throws IOException{
		//check if greater than 0
		if(Integer.valueOf(player2PairPlus.getText()) > 0){
			//decrement by 5
			player2PairPlus.setText(String.valueOf(Integer.valueOf(player2PairPlus.getText()) - 5));
		}

	}

	public void play_again(ActionEvent e) throws IOException{
		reset();
	}

	public void refresh (ActionEvent e) throws IOException{

		//pop out window asking user if sure

		reset();

		//reset banks
		game = new Game();

		//reset bank UI
		p1bank.setText(String.valueOf(game.p1.totalWinnings));
		p2bank.setText(String.valueOf(game.p2.totalWinnings));

		//reset bet amount UI
		player1Ante.setText(String.valueOf(5));
		player2Ante.setText(String.valueOf(5));
		player1PairPlus.setText(String.valueOf(5));
		player2PairPlus.setText(String.valueOf(5));




	}
}
