import java.util.*;

class deck
{
	private ArrayList<Card> deck;
	private ArrayList<Card> hand;
	public void initialize_deck()
	{
		Card [] cards = new Card[52];
		int k =0;
		for(int i = 1; i < 14; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				if(j == 0) 
				{
					cards[k] = new Card(i, "C");
				}
				else if(j == 1) 
				{
					cards[k] = new Card(i, "D");
				}
				else if(j == 2) 
				{
					cards[k] = new Card(i, "H");
				}
				else 
				{
					cards[k] = new Card(i, "S");
				}
				k++;
			}
		}
		for(int i = 0; i < cards.length; i++)
		{
			deck.add(cards[i]);
		}
	}
	
	public void deal()
	{
		if(deck.size() == 0)
		{
			this.initialize_deck();
			for(int k = 0;k < 15; k++) this.shuffle();
		}
		hand.add(deck.remove(deck.size()-1));
	}
	
	public void shuffle()
	{
		int index;
		Card tmp;
		if(hand.size() > 0) clearHand();
		for(int i = 0; i < deck.size()-1; i++)
		{
			index = 0 + (int)(Math.random() * ((deck.size()-1 - 0) + 1));
			tmp = deck.get(index);
			deck.set(index, deck.get(i));
			deck.set(i, tmp);
		}
	}
	
	public void clearHand()
	{
		while(hand.size() != 0)
		{
			hand.remove(hand.size()-1);
		}
	}
	
	public void printdeck()
	{
		for(int i = 0; i < deck.size()-1; i++)
		{
			Card c = deck.get(i);
			System.out.println(c.getFriendly());
		}
	}
	
	public String printhand()
	{
		String h = "";
		for(int i = 0; i < hand.size(); i++)
		{
			Card c = hand.get(i);
			h += "[ " +c.getFriendly() +" ]";
		}
		return h;
	}
	
	public int getsum()
	{
		int sum = 0;
		for(int i = 0;i < hand.size(); i++)
		{
			Card c = hand.get(i);
			sum += c.getDenomination();
		}
		return sum;
	}
	
	public int getcount()
	{
		Card c = hand.get(hand.size() -1);
		int denomination = c.getDenomination();
		if(denomination >= 2 && denomination <= 6) return 1;
		if(denomination >= 7 && denomination <= 9) return 0;
		return -1;
	}
	
	public int getdecksize()
	{
		return deck.size();
	}
	
	public int gethandsize()
	{
		return hand.size();
	}
	
	public deck()
	{
		deck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		initialize_deck();
	}
}

class cards
{
	public static void main(String [] args)
	{
	}
}
