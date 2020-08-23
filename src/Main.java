import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.Action;

public class Main {

	private JFrame frame;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//creating frame
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//creating button for guessing game
		JButton guessGame = new JButton("Guess Game");
		guessGame.setAction(action);
		guessGame.setBounds(88, 92, 113, 44);
		frame.getContentPane().add(guessGame);
		
		//button for black jack game
		JButton blackJack = new JButton("Blackjack");
		blackJack.setAction(action_1);
		blackJack.setBounds(229, 92, 113, 44);
		frame.getContentPane().add(blackJack);
		
		//label to ask user which game they would like
		JLabel lblNewLabel = new JLabel("Which Game Would You Like To Play?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(94, 24, 232, 32);
		frame.getContentPane().add(lblNewLabel);
		
		//button for authorship
		JButton authorship = new JButton("Authorship");
		authorship.setAction(action_2);
		authorship.setBounds(148, 186, 140, 44);
		frame.getContentPane().add(authorship);
	}
	
	//action to call on the guess game
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Guess Game");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new GuessGame();
				frame.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	//action to call on the black jack game
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Blackjack");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new BlackJack();
				frame.dispose();
				System.out.println("test");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	//action to display authorship
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Authorship");
		}
		public void actionPerformed(ActionEvent e) {
			// create a dialog Box 
            JDialog d = new JDialog(frame, "Rules"); 
  
            // create a label 
            JLabel l = new JLabel("<html><div style='text-align:center;'>"
            		+ " - Developer: Gerardo Guzman-Navas"
            		+ "<br>- Class: IT211"
            		+ "<br>- Assignment: Final Project Card Game"
            		+ "<br>- Date: August 22, 2020"
            		+ "</div></html>"); 
            d.getContentPane().add(l); 
  
            // setsize of dialog 
            d.setSize(250, 250); 
  
            // set visibility of dialog 
            d.setVisible(true);
		}
	}
}
