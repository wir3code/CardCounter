import java.util.*;

class Card
{
	/*
		@var suit - holds the suit (ace, spade, club, heart) of card
		@var denomination - holds the nominal value of card (ace through king)
		@var suits - holds user-friendly values of suits
		@var denominations - holds user-friendly values of denominations
	*/
	private String suit;
	private int denomination;
	
	private static String [] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
	private static String [] denominations = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	
	public void setDenomination(int d)
	{
		this.denomination = d;
	}
	
	public void setSuit(String s)
	{
		this.suit = s;
	}
	
	public int getDenomination()
	{
		if(this.denomination > 10) return 10; //For adjusting jacks, queens and kings to their proper nominal value
		return this.denomination;
	}
	
	public String getSuit()
	{
		return this.suit;
	}
	
	public String getFriendly()
	{
		String cardName = denominations[this.denomination-1] + " Of ";
		if(this.suit.equals("C")) cardName += suits[0];
		else if(this.suit.equals("D")) cardName += suits[1];
		else if(this.suit.equals("H")) cardName += suits[2];
		else cardName += suits[3];
		return cardName;
	}
	/*
		Constructor, takes two arguments for initializing suit and denomination variables
	*/
	public Card(int d, String s)
	{
		setDenomination(d);
		setSuit(s);
	}
}