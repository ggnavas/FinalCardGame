import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DeckOfCards {
	public static BlackJackCard[] deck;
	// private int currentCard;

	public DeckOfCards() throws IOException {

		// create deck array for card objects
		deck = new BlackJackCard[52];

		int deckNum = 0;
		int deckImage = 1;

		for (int i = 0; i < 4; i++) { // runs through 4 times for the number of suits

			for (int j = 1; j < 14; j++) { // runs 13 times for the value of cards

				// creation of the card object
				deck[deckNum] = new BlackJackCard(j, i, ImageIO.read(new File("images/" + deckImage + ".gif")));
				deckNum++;
				deckImage++;
			}
		}
	}

	// shuffle method for shuffling the deck
	public void shuffle() {

		int deckLength = deck.length - 1;

		for (int i = 0; i < deckLength; i++) {
			int r = i + (int) (Math.random() * (deckLength - i));
			BlackJackCard temp = deck[r];
			deck[r] = deck[i];
			deck[i] = temp;
		}
	}

	// method for dealing the top card in the deck
	public int dealTopCard() {
		int topCard = -1;

		if (deck.length > 0) {
			topCard++;
			return deck[topCard].getRank();

		} else {
			return 0;
		}
	}
}
