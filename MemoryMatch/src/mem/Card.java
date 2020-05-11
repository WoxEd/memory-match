/*
 * Author: Eddy Zhang
 * Card.java
 * Date: May 10th 2020
 * Description: This is the Card class which contains the features of each card on the came board
 */
package mem;

import javax.swing.JButton;

public class Card extends JButton
{
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_VIEW = "<  >";
	private String detail;
	private boolean flipped;
	
	public Card(String detail)
	{
		setDetail(detail);
		back();
	}
	
	public String getDetail()
	{
		return this.detail;
	}
	
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
	
	public void flip()
	{
		setText(getDetail());
		setFlipped(true);
	}
	
	public void back()
	{
		setText(getDefault());
		setFlipped(false);
	}
	
	public String getDefault()
	{
		return DEFAULT_VIEW;
	}
	
	public boolean isFlipped()
	{
		return this.flipped;
	}
	
	private void setFlipped(boolean flipped)
	{
		this.flipped = flipped;
	}
}
