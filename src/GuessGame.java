import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuessGame implements ActionListener, ItemListener {

	
		public DeckOfCards deck;

		// for the GUI
		Card backCard = new Card(100, 100, ImageIO.read(new File("images/b.gif")));
		JFrame window = new JFrame("Card Game!");
		JButton button = new JButton("Deal");
		JPanel contentPane = new JPanel(new BorderLayout());
		JPanel suitPanel = new JPanel(new BorderLayout());
		JPanel valuePanel = new JPanel(new BorderLayout());
		JLabel backImage = new JLabel(new ImageIcon(backCard.getCardImage()));
		JLabel cardImage;
		JLabel gameStats;
		ImageIcon imgIcon;
		JComboBox suitSelection;
		JComboBox valueSelection;

		// for the game logic
		int suit = 0; // user card suit
		int value = 1; // user card value
		int nextCard = 0; // to switch to next card on deck
		int userCardRank = 1; // the rank of the user card
		int win = 0; // number of times the cards match
		int loss = 0; // number of times the cards don't match
		int rmngCards = 52; // total number of remaining cards

		public GuessGame() throws IOException {

			// instantiating the deck array
			deck = new DeckOfCards();

			// shuffle deck
			deck.shuffle();

			// printing out rank and card name in the console.. for cheating (:
			System.out.println(DeckOfCards.deck[nextCard].getRank());
			System.out.println(DeckOfCards.deck[nextCard].toString());

			((JFrame) window).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			button.addActionListener(this);
			button.setSize(300, 400);

			// creating panel in window
			contentPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
			contentPane.setLayout(new GridLayout(2, 3, 20, 20));
			suitPanel.setLayout(new GridLayout(0, 1));
			valuePanel.setLayout(new GridLayout(0, 1));

			// creating card image
			imgIcon = new ImageIcon();
			cardImage = new JLabel(imgIcon);

			// creating label for the game stats
			gameStats = new JLabel("<html>Welcome!<br>" + "Wins: " + win + "<br> Loss: " + loss + "<br>Cards Left: "
					+ rmngCards + "</html>");

			// combo box for user card suits
			JLabel suitLabel = new JLabel("Pick Your Suit");
			String[] suits = { "Spades", "Hearts", "Diamonds", "Clubs" };
			suitSelection = new JComboBox(suits);

			// combo box for user card value
			JLabel valueLabel = new JLabel("Pick Your Value");
			String[] value = { "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King" };
			valueSelection = new JComboBox(value);

			// adding event listeners to combo box
			suitSelection.addItemListener(this);
			suitPanel.add(suitLabel);
			suitPanel.add(suitSelection);
			valueSelection.addItemListener(this);
			valuePanel.add(valueLabel);
			valuePanel.add(valueSelection);

			// Adding labels to panel
			contentPane.add(backImage);
			contentPane.add(gameStats);
			contentPane.add(cardImage);
			contentPane.add(valuePanel);
			contentPane.add(suitPanel);
			contentPane.add(button);

			// adding main panel to window frame
			window.add(contentPane, BorderLayout.CENTER);
			window.pack();
			window.setVisible(true);

		}
	
	public void actionPerformed(ActionEvent e) {

		imgIcon.setImage(DeckOfCards.deck[nextCard].getCardImage());
		cardImage.repaint();

		// if the cards match
		if (DeckOfCards.deck[nextCard].getRank() == userCardRank) {

			System.out.println("you got it");
			rmngCards--;
			win++;
		}

		// if the cards don't match
		else {
			System.out.println("wrong");
			rmngCards--;
			loss++;
		}

		gameStats.setText("<html>Welcome!<br>" + "Wins: " + win + "<br> Loss: " + loss + "<br>Cards Left: " + rmngCards
				+ "</html>");
		nextCard++;
	}

	// getting the user card
	@Override
	public void itemStateChanged(ItemEvent e) {

		suit = suitSelection.getSelectedIndex();
		value = valueSelection.getSelectedIndex() + 1;

		userCardRank = (suit * 13) + value;
	}

}
