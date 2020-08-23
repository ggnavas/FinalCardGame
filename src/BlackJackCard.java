import java.awt.image.BufferedImage;

public class BlackJackCard extends Card{

	public BlackJackCard(int theValue, int theSuit, BufferedImage card) {
		super(theValue, theSuit, card);
		// TODO Auto-generated constructor stub
	}
	
	//method for card values in the black jack game
	public int blackJackValue(int gameValue) {
		//if card has a values over 10(king, jack, queen)
		if(getValue() > 10) {
			//make value 10
			return 10;
		}
		
		//if card value is 1 (ace)
		else if(getValue() == 1) {
			//checking if playerValue is less than 11
			if(gameValue < 11) {
				//return 11
				return 11;
			}
			//if playerValue is more than 11
			else {
				//make value 1
				return 1;
			}
		}
		//if its any other card (value 2-10)
		else {
			//return regular value
			return getValue();
		}
	}
}
