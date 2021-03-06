 package poker.app.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import exceptions.DeckException;
import netgame.common.Hub;
import pokerBase.Action;
import pokerBase.Card;
import pokerBase.Deck;
import pokerBase.GamePlay;
import pokerBase.GamePlayPlayerHand;
import pokerBase.Player;
import pokerBase.Rule;
import pokerBase.Table;
import pokerEnums.eAction;
import pokerEnums.eGame;
import pokerEnums.eGameState;
import poker.app.*;
import poker.app.view.RootLayoutController;

public class PokerHub extends Hub {

	private Table HubPokerTable = new Table();
	private GamePlay HubGamePlay;
	private int iDealNbr = 0;
	//private PokerGameState state;
	private eGameState eGameState;
	private RootLayoutController rlc;

	public PokerHub(int port) throws IOException {
		super(port);
	}

	protected void playerConnected(int playerID) {

		if (playerID == 2) {
			shutdownServerSocket();
		}
	}

	protected void playerDisconnected(int playerID) {
		shutDownHub();
	}

	protected void messageReceived(int ClientID, Object message) {

		if (message instanceof Action) {
			Action act = (Action) message;
			switch (act.getAction()) {
			case GameState:
				sendToAll(HubPokerTable);
				break;
			case TableState:
				resetOutput();
				sendToAll(HubPokerTable);
				break;
			case Sit:
				resetOutput();
				HubPokerTable.AddPlayerToTable(act.getPlayer());				
				sendToAll(HubPokerTable);				
				break;
			case Leave:
				resetOutput();
				HubPokerTable.RemovePlayerFromTable(act.getPlayer());
				sendToAll(HubPokerTable);				
				break;
				
			case StartGame:
				System.out.println("Starting Game!");
				resetOutput();
				
				// TODO - Lab #5 Do all the things you need to do to start a game!!
				
				//	X Determine which game is selected (from RootTableController)
				//		1 line of code
				//	X Get the Rule based on the game selected
				//		1 line of code
				
				for (eGame gameRuleName : eGame.values()) {
					//if the rulename in RLC == enum's ruleName String
					if (rlc.getRuleName() == gameRuleName.toString()){
						//then define a variable ruleset 
						eGame ruleSet = gameRuleName;
						//get the rule set
						
						Rule gameRule = new Rule(ruleSet);
						//UUID dealerID = UUID.randomUUID();
						
						
						//John's Code
						ArrayList<UUID> keysList = new ArrayList<UUID>(HubPokerTable.getHashPlayers().keySet());
						Collections.shuffle(keysList);
						UUID dealerID  = keysList.get(0);
						//
						
						//Auzi's Code
						GamePlay newGame = new GamePlay(gameRule, dealerID);
						newGame.setGameDeck(new Deck(newGame.getRule().GetNumberOfJokers(),newGame.getRule().GetRuleCards()));
						//
						
						
					}
					
				}
				
				
				
				//	The table should eventually allow multiple instances of 'GamePlay'...
				//		Each game played is an instance of 'GamePlay'...
				//		For the first instance of GamePlay, pick a random player to be the 
				//		'Dealer'...  
				//		< 5 lines of code to pick random player
				
				
				  
				//	Start a new instance of GamePlay, based on rule set and Dealer (Player.PlayerID)
				//		1 line of code

				
				
				//	There are 1+ players seated at the table... add these players to the game
				//		< 5 lines of code
				//for (player : playerm)
				
				//	GamePlay has a deck...  create the deck based on the game's rules (the rule
				//		will have number of jokers... wild cards...
				//		1 line of code

				//	Determine the order of players and add each player in turn to GamePlay.lnkPlayerOrder
				//	Example... four players playing...  seated in Position 1, 2, 3, 4
				//			Dealer = Position 2
				//			Order should be 3, 4, 1, 2
				//	Example...  three players playing... seated in Position 1, 2, 4
				//			Dealer = Position 4
				//			Order should be 1, 2, 4
				//		< 10 lines of code
				
				//	Set PlayerID_NextToAct in GamePlay (next player after Dealer)
				//		1 line of code
				
				//	Here's how to get an eNum based on a given value (Merry Christmas):)
				eGame Game = eGame.getGame(1);

				//	Send the state of the game back to the players
				sendToAll(HubGamePlay);
				break;
			case Deal:
				
				/*
				int iCardstoDraw[] = HubGamePlay.getRule().getiCardsToDraw();
				int iDrawCount = iCardstoDraw[iDealNbr];

				for (int i = 0; i<iDrawCount; i++)
				{
					try {
						Card c = HubGamePlay.getGameDeck().Draw();
					} catch (DeckException e) {
						e.printStackTrace();
					}
				}
*/
				break;
			}
		}

		System.out.println("Message Received by Hub");
	}

}