import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Button;
import javax.swing.JTextPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;

public class BlackJack {
	
	//creating the deck object
	public DeckOfCards deck = new DeckOfCards();
	
	//initializing the JFrame object
	public JFrame frame;
	
	//creating the back card image object
	Card backCard = new Card(100, 100, ImageIO.read(new File("images/b.gif")));
	
	//Panels
	JPanel dealerHand = new JPanel();
	JPanel userHand = new JPanel();
	
	//Buttons
	JButton dealButton = new JButton("Deal");
	JButton hitButton = new JButton("Hit");
	JButton stayButton = new JButton("Stay");
	JButton restartButton = new JButton("Restart");
	
	//Labels
	JLabel gameStatus = new JLabel("");
	JLabel firstCard = new JLabel(new ImageIcon(backCard.getCardImage()));
		
	//Game Logic
	int nextCard = 4;
	int playerValue = 0;
	int dealerValue = 0;
	public final Action action = new SwingAction();
	public final Action action_1 = new SwingAction_1();
	private final JButton authorshipButton = new JButton("Authorship");
	private final Action action_3 = new SwingAction_3();

	public BlackJack() throws IOException{
		
		deck.shuffle(); //Shuffling deck at the beginning of the game
		
		//creating the frame
		frame = new JFrame();
		frame.setVisible(true);
		frame.setBounds(100, 100, 726, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Adding more cards to the player function
		hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//creates a label for the next card and puts it i the panel
				userHand.add(new JLabel(new ImageIcon(DeckOfCards.deck[nextCard].getCardImage())));
				//updates the user value from the new card
				playerValue += DeckOfCards.deck[nextCard].blackJackValue(playerValue);
				nextCard++;
				
				//refresh the panel to show new card
				userHand.updateUI();
				
				//update game status
				gameStatus.setText("<html><p>Player Score: " + playerValue + "</p></html>");
				checkGameStatus();
			}
		});
		//finish creating the button and adding to frame
		hitButton.setBounds(147, 383, 89, 23);
		hitButton.setEnabled(false);
		frame.getContentPane().add(hitButton);
		
		//label for deck
		JLabel deckLabel = new JLabel("Deck");
		deckLabel.setBounds(27, 17, 76, 14);
		frame.getContentPane().add(deckLabel);
		
		//label for dealer hand panel
		JLabel dealerPanel = new JLabel("Dealer Hand");
		dealerPanel.setBounds(361, 17, 76, 14);
		dealerPanel.setBackground(null);
		frame.getContentPane().add(dealerPanel);
		
		//label for user hand panel
		JLabel userPanel = new JLabel("User Hand");
		userPanel.setBounds(361, 190, 76, 14);
		frame.getContentPane().add(userPanel);
		
		//back of card label
		JLabel backImage = new JLabel(new ImageIcon(backCard.getCardImage()));
		backImage.setBounds(27, 42, 76, 107);
		frame.getContentPane().add(backImage);
		
		//creating the dealer panel
		dealerHand.setBounds(179, 42, 415, 137);
		dealerHand.setVisible(false);
		frame.getContentPane().add(dealerHand);
		
		//creating the user panel
		userHand.setBounds(179, 215, 415, 137);
		frame.getContentPane().add(userHand);
		
		//creating the deal button
		dealButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//displaying the dealer panel
				dealerHand.setVisible(true);
				
				//enabling the buttons
				stayButton.setEnabled(true);
				hitButton.setEnabled(true);
				
				//dealing two cards to player and dealer
				dealerHand.add(firstCard);				
				userHand.add(new JLabel(new ImageIcon(DeckOfCards.deck[1].getCardImage())));				
				dealerHand.add(new JLabel(new ImageIcon(DeckOfCards.deck[2].getCardImage())));				
				userHand.add(new JLabel(new ImageIcon(DeckOfCards.deck[3].getCardImage())));
				
				//refreshing panels to update panels
				dealerHand.updateUI();
				userHand.updateUI();
				
				//calling on a method to update value
				initialValue();
				dealButton.setEnabled(false); //to disable the button
				
				//setting initial game status
				gameStatus.setText("<html><p>Player Score: " + playerValue + "</p></html>");
			}
		});
		dealButton.setBounds(27, 383, 89, 23);
		frame.getContentPane().add(dealButton);
		
		//game status label
		gameStatus.setBounds(33, 215, 106, 137);
		frame.getContentPane().add(gameStatus);
		stayButton.setAction(action);
		
		//Creating stay button
		stayButton.setBounds(271, 383, 89, 23);
		stayButton.setEnabled(false);
		frame.getContentPane().add(stayButton);
		restartButton.setAction(action_1); //setting action when button is clicked
		
		//creating the restart button
		restartButton.setBounds(394, 383, 89, 23);
		restartButton.setEnabled(false);
		frame.getContentPane().add(restartButton);
		authorshipButton.setAction(action_3); //setting action when button is clicked
		authorshipButton.setBounds(587, 383, 101, 23);
		
		frame.getContentPane().add(authorshipButton);
		}

		//set the initial value and dealer value when deal button is clicked
		public void initialValue() {		
			dealerValue += DeckOfCards.deck[0].blackJackValue(dealerValue);
			playerValue += DeckOfCards.deck[1].blackJackValue(playerValue);
			dealerValue += DeckOfCards.deck[2].blackJackValue(dealerValue);
			playerValue += DeckOfCards.deck[3].blackJackValue(playerValue);	
		}
		
		//checking game status
		public void checkGameStatus() {
			
			//when the player value goes over 21
			if(playerValue > 21) {
				
				//call on methods that updates back of card
				updateBackCard();
				
				//let the user know it's a bust
				gameStatus.setText("Bust");
			}
		}
		
		public void updateBackCard() {
			//display the back faced image
			firstCard = new JLabel(new ImageIcon(DeckOfCards.deck[0].getCardImage()));
			dealerHand.remove(0);
			dealerHand.add(firstCard, 0);
			dealerHand.updateUI();
			
			//disable all buttons, and enable restart button
			hitButton.setEnabled(false);
			stayButton.setEnabled(false);
			restartButton.setEnabled(true);
		}
	
	//action when the user clicks Stay
	public class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Stay");
		}
		public void actionPerformed(ActionEvent e) {
				
				//call on methods that updates back of card
				updateBackCard();
				
				//making sure dealer value is more than 16
				while(dealerValue < 17) {
				//if it is deal another card to dealer and update value
				dealerValue += DeckOfCards.deck[nextCard].blackJackValue(dealerValue);
				dealerHand.add(new JLabel(new ImageIcon(DeckOfCards.deck[nextCard].getCardImage())));
				dealerHand.updateUI();
				nextCard++;
				}
				
				//if player got higher than dealer or dealer got over 21
				if(playerValue > dealerValue || dealerValue > 21) {
					
					//let the user know s/he won
					gameStatus.setText("<html><p>Player Wins!"
										+ "<br> Player Score: " + playerValue
										+ " <br> Dealer Score: " + dealerValue +"</p></html>");
				}
				//if player and dealer got same score
				else if(playerValue == dealerValue) {
					//let the user know its a tie
					gameStatus.setText("<html><p>It's a Tie!"
							+ "<br> Player Score: " + playerValue
							+ " <br> Dealer Score: " + dealerValue +"</p></html>");
				}
				// if non of the above conditions are met, dealer wins
				else {
					//let user know dealer won
					gameStatus.setText("<html><p>Dealer Wins!"
							+ "<br> Player Score: " + playerValue
							+ " <br> Dealer Score: " + dealerValue +"</p></html>");
				}
		}
	}
	
	//when the reset button is clicked
	public class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Restart");
		}
		public void actionPerformed(ActionEvent e) {
			//shuffle deck again
			deck.shuffle();
			
			//reset everything
			dealerHand.removeAll();
			userHand.removeAll();
			dealerHand.updateUI();
			userHand.updateUI();
			gameStatus.setText("");
			dealerValue = 0;
			playerValue = 0;
			nextCard = 0;
			dealButton.setEnabled(true);
			firstCard = new JLabel(new ImageIcon(backCard.getCardImage()));
			restartButton.setEnabled(false);
			System.out.println("Restart Button Works!");
		}
	}
	
	//when the rules button is clicked
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Rules");
		}
		public void actionPerformed(ActionEvent e) {
			// create a dialog Box 
            JDialog RulesDialog = new JDialog(frame, "Rules"); 
  
            // create a label and set the rules of the game
            JLabel RulesLabel = new JLabel("<html><div style='text-align:center;'>Rules: <br> "
            		+ " - Stay Under 21"
            		+ "<br>- Score Higher Than The Dealer"
            		+ "<br>- Click Deal To Start The Game"
            		+ "<br>- Click Hit To Get Another Card"
            		+ "<br>- Click Stay To Stay With Current Deck"
            		+ "<br>- Click Restart To Reset The Whole Game"
            		+ "<br>- Have FUN! (: </div></html>"); 
            RulesDialog.getContentPane().add(RulesLabel); 
  
            // setsize of dialog 
            RulesDialog.setSize(250, 250); 
  
            // set visibility of dialog 
            RulesDialog.setVisible(true);
		}
	}
}
