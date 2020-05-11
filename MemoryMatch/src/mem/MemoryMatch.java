/*
 * Author: Eddy Zhang
 * MemoryMatch.java
 * Date: May 10th 2020
 * Description: This is a memory matching game created within a JFrame
 */
package mem;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MemoryMatch
{
	private static final int HEIGHT = 1000;
	private static final int WIDTH = 1000;
	private static final int DEFAULT_DIFFICULITY = 6;
	private static final String TITLE = "Memory Matching Game";
	private static ArrayList<JButton> buttons = new ArrayList<>(); //arraylist to hold buttons for easy reference
	private static JLabel numMoves = new JLabel();
	private static JLabel remaining = new JLabel();
	private static JPanel mainPanel = new JPanel();
	private static Board board;
	

	public static void main(String[]args)
	{
		setBoard(new Board(DEFAULT_DIFFICULITY));
		JFrame frame = new JFrame();
		frame = new JFrame();
		frame.setTitle(TITLE + " " + board.getDifficulty());
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setSize(HEIGHT,WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getMainPanel().setLayout(new BorderLayout());
		getMainPanel().add(addButtons(),BorderLayout.SOUTH);
		getMainPanel().add(getBoard(),BorderLayout.CENTER);
		getMainPanel().add(addLabels(),BorderLayout.NORTH);
		
		frame.add(getMainPanel());
	}
	
	private static JPanel addButtons()
	{
		JPanel buttons = new JPanel();
		
		JButton changeDifficulty = new JButton("Change Difficulty");
		JButton easy = new JButton("Easy: 3x4 [12 cards]");
		JButton medium = new JButton("Medium: 4x5 [20 cards]");
		JButton hard = new JButton("Hard Mode: 6x6 [36 cards]");
		JButton impossible = new JButton("Impossible Mode: 8x9 [72 cards]");
		
		changeDifficulty.addActionListener(e -> menuMode(true));
		easy.addActionListener(e -> {newLevel(6); menuMode(false);});
		medium.addActionListener(e -> {newLevel(10); menuMode(false);});
		hard.addActionListener(e -> {newLevel(18); menuMode(false);});
		impossible.addActionListener(e -> {newLevel(36); menuMode(false);});

		getButtons().add(changeDifficulty);
		getButtons().add(easy);
		getButtons().add(medium);
		getButtons().add(hard);
		getButtons().add(impossible);

		for(JButton b:getButtons())
		{
			buttons.add(b);
		}
		menuMode(false);
		return buttons;
	}
	
	private static JPanel addLabels()
	{
		JPanel labels = new JPanel();
		labels.setLayout(new BorderLayout());
		updateLabel();
		
		labels.add(getRemaining(),BorderLayout.NORTH);
		labels.add(getNumMoves(),BorderLayout.SOUTH);
		return labels;
	}
	
	private static void menuMode(boolean b)
	{
		getButtons().get(0).setVisible(!b); //change difficulty
		getButtons().get(1).setVisible(b); //easy
		getButtons().get(2).setVisible(b); //medium
		getButtons().get(3).setVisible(b); //hard
		getButtons().get(4).setVisible(b); //impossible
	}
	
	public static void updateLabel()
	{
		getRemaining().setText("Cards Remaining: " + getBoard().getRemaining());
		getNumMoves().setText("Number of Moves: " + getBoard().getNumMoves());
	}
	
	public static void newLevel(int size)
	{
		getMainPanel().remove(getBoard());
		setBoard(new Board(size));
		updateLabel();
		getMainPanel().add(getBoard(),BorderLayout.CENTER);
	}
	
	public static void setBoard(Board board)
	{
		MemoryMatch.board = board;
	}
	
	public static Board getBoard()
	{
		return MemoryMatch.board;
	}
	
	public static JLabel getNumMoves() 
	{
		return numMoves;
	}

	public static JLabel getRemaining() {
		return remaining;
	}

	public static JPanel getMainPanel() 
	{
		return mainPanel;
	}
	
	public static ArrayList<JButton> getButtons()
	{
		return MemoryMatch.buttons;
	}

	
	
	
	
}
