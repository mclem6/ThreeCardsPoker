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

		//show cards
			update_p1_card_UI();
			update_p2_card_UI();

			//update banks
			p1bank.setText(String.valueOf(game.p1.totalWinnings));
			p2bank.setText(String.valueOf(game.p2.totalWinnings));


			//wait listview
			game_info.getItems().add(String.valueOf(game.p1.totalWinnings));
			game_info.getItems().add(String.valueOf(game.p2.totalWinnings));

			//wait 3 seconds

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

	//press play button action
	public void pressPlay(ActionEvent e)throws IOException{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/playScene.fxml"));
		Parent root2 = loader.load();
		MyController myctr = loader.getController();
		root2.getStylesheets().add("/styles/stylePlay.css");
		root1.getScene().setRoot(root2);

		//initiliaze game
		myctr.game = new Game();

		myctr.game_info.getItems().add("game starting");



	}	

	public void pressExit(ActionEvent e)throws IOException{
		Platform.exit();

	}	


	// -------- PLAYER 1 ------------
	public void player1Bet(ActionEvent e)throws IOException{

		//update 
		game.update_p1_bet(Integer.valueOf(player1Ante.getText()), Integer.valueOf(player1PairPlus.getText()));
		

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
		game.p1_play_wager_made();

		//check to see if p2 has selected
		if(game.p2.bet_set){
			
			update_UI_result();



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
		game.update_p2_bet(Integer.valueOf(player2Ante.getText()), Integer.valueOf(player2PairPlus.getText()));

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
		game.p2_play_wager_made();

		//check to see if p1 has selected
		if(game.p1.bet_set){

			update_UI_result();
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

		play_again_button.setDisable(true);

	}
    
}
