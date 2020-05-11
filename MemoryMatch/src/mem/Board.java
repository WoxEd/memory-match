/*
 * Author: Eddy Zhang
 * Board.Java
 * Date: May 10th 2020
 * Description: This is the game board where the details of the game are programmed
 */
package mem;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static final String[] BOARD_PIECES = 
		{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
		,"0","1","2","3","4","5","6","7","8","9"}; //list of all possible board pieces
	
	private int row;
	private int col;
	private int uniqueCards;
	private int numElements;
	private static final int EASY = 6;
	private static final int MEDIUM = 10;
	private static final int HARD = 18;
	private static final int IMPOSSIBLE = 36;
	private Card lastClicked;
	private int numClicked;
	private int remaining;
	private int numMoves;
	
	
	public Board(int size)
	{
		setUniqueCards(size);
		setNumElements(size*2);
		setRow(size);
		setCol(size);
		makeBoard(size);
		setLastClicked(null);
		setNumClicked(0);
		setRemaining(size*2);
		setNumMoves(0);
	}
	
	private ArrayList<String> createElements()
	{ 	//randomly selects a number of elements to add to the board
		//puts two of each into an arraylist then shuffles it
		ArrayList<String> boardPieces = new ArrayList<>();
		Collections.addAll(boardPieces, BOARD_PIECES);
		
		ArrayList<String> shuffledBoard = new ArrayList<>();
		
		for(int i=0; i<getUniqueCards(); i++)
		{
			int random = (int)(Math.random()* boardPieces.size());
			shuffledBoard.add(boardPieces.get(random));
			shuffledBoard.add(boardPieces.get(random));
			boardPieces.remove(random);
		}
		Collections.shuffle(shuffledBoard);
		return shuffledBoard;
	}
	
	public void makeBoard(int size)
	{
		setLayout(new GridLayout(getRow(),getCol()));
		ArrayList<String> elements = createElements();
		
		for(String text : elements)
		{
			Card card = new Card(text);
			card.setFont(new Font("KongBot", Font.BOLD, 40));
			card.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent event) 
					{
						Card card = (Card) event.getSource();
						if(!card.isFlipped() && getNumClicked() < 2)
						{
							addClicked();
							card.flip();
							if(getLastClicked() == null)
							{
								setLastClicked(card);
							}
							else
							{
								setNumMoves(getNumMoves()+1);
								Timer timer = new Timer(1000, new ActionListener() 
								{
						             @Override
						             public void actionPerformed(ActionEvent e) 
						             {
						            	 getLastClicked().back();
						            	 card.back();
						            	 clearMoves();
						             }
						          });

									if(card.getDetail() != getLastClicked().getDetail())
									{
									  timer.setRepeats(false);
							          timer.start();
									}
									else
									{
										card.setBackground(Color.YELLOW);
										getLastClicked().setBackground(Color.YELLOW);
										clearMoves();
										setRemaining(getRemaining()-2);
									}
									MemoryMatch.updateLabel();
									
								}
							}
						}
						
					});
			add(card);
		}
	}
	
	private void clearMoves()
	{
		setLastClicked(null);
		setNumClicked(0);
	}
	
	private void setLastClicked(Card card) 
	{
		this.lastClicked = card;	
	}
	
	private Card getLastClicked()
	{
		return this.lastClicked;
	}

	public int getCol() 
	{
		return col;
	}

	public int getRow() 
	{
		return this.row;
	}
	
	public void setCol(int size) 
	{
		switch(size)
		{
		case EASY:
			this.col = 4;
			break;
		case MEDIUM:
			this.col = 5;
			break;
		case HARD:
			this.col = 6;
			break;
		case IMPOSSIBLE:
			this.col = 9;
			break;
		default:
			this.col = 1;
			break;
		}
	}
	public void setRow(int size) 
	{
		switch(size)
		{
		case EASY:
			this.row = 3;
			break;
		case MEDIUM:
			this.row = 4;
			break;
		case HARD:
			this.row = 6;
			break;
		case IMPOSSIBLE:
			this.row = 8;
			break;
		default:
			this.row = 1;
			break;
		}
	}
	
	public String getDifficulty()
	{
		switch(getUniqueCards()) 
		{
			case EASY:
				return "(Easy: 12 Cards)";
			case MEDIUM:
				return "(Medium: 20 Cards)";
			case HARD:
				return "(Hard: 36 Cards)";
			case IMPOSSIBLE:
				return "(Impossible: 72 Cards)";
			default:
				return "(Nothing)";
		}
	}
	

	public int getNumElements() 
	{
		return this.numElements;
	}

	public void setNumElements(int numElements) 
	{
		this.numElements = numElements;
	}

	private void setUniqueCards(int uniqueCards) 
	{
		this.uniqueCards = uniqueCards;
	}
	
	public int getUniqueCards()
	{
		return this.uniqueCards;
	}
	
	public void addClicked()
	{
		this.numClicked++;
	}
	
	public void setNumClicked(int numClicked)
	{
		this.numClicked = numClicked;
	}
	
	public int getNumClicked()
	{
		return this.numClicked;
	}
	
	public int getRemaining() 
	{
		return this.remaining;
	}

	public void setRemaining(int remaining) 
	{
		this.remaining = remaining;
	}

	public int getNumMoves() 
	{
		return this.numMoves;
	}

	public void setNumMoves(int numMoves) 
	{
		this.numMoves = numMoves;
	}	
}
