import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class casinogui extends JFrame
{
	private JLabel gBalance;
	private JLabel gCount;
	private JLabel gSum;
	private JLabel gDeckSize;
	
	private JTextArea gameplay;
	private JTextField bet_amount;
	private JButton start_game;
	private JButton hit;
	private JButton deal;
	
	private deck d;
	private int p1_sum;
	private int p2_sum;
	private int winner;
	private int count;
	private double balance;
	private double bet;
	
	class StartListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
						if(d.getdecksize() == 52) count = 0;
			if(bet_amount.getText().equals(""))
			{
				gameplay.append("\nPlease input bet amount\n");
			}
			else
			{
				p1_sum = 0;
				gameplay.append("\nBetting amount: " + Double.parseDouble(bet_amount.getText()) + "\n");
				gameplay.append("Shuffling deck...\n");
				bet_amount.setEditable(false);
				for(int k = 0; k < 50; k++) d.shuffle();
				gameplay.append("Dealing cards\n");
				d.deal();
				count += d.getcount();
							if(d.getdecksize() == 52) count = 0;

				d.deal();
							if(d.getdecksize() == 52) count = 0;

				count+= d.getcount();
				p1_sum = d.getsum();
				gameplay.append("Hand: " + d.printhand() + "\n");
				gCount.setText("Count: " + count);
				gSum.setText("Hand sum: " + p1_sum);
				gBalance.setText("Balance: " + balance);
				start_game.setEnabled(false);
				gDeckSize.setText("Deck: " + d.getdecksize());
			}
		}
	}
	
	class HitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(d.getdecksize() == 52) count = 0;
			d.deal();
			count += d.getcount();
			gameplay.append("Hand: " + d.printhand() + "\n");
			gCount.setText("Count: " + count);
			p1_sum = d.getsum();
			gSum.setText("Hand sum: " + p1_sum);
			gDeckSize.setText("Deck: " + d.getdecksize());
			if(p1_sum == 21)
			{
				winner = 1;
				hit.setEnabled(false);
				deal.doClick();
			}
			if(p1_sum > 21)
			{
				winner = 2;
				hit.setEnabled(false);
				deal.doClick();
			}
		}
	}
	
	class StickListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(d.getdecksize() == 52) count = 0;
			if(winner == 0)
			{
				p1_sum = d.getsum();
				d.clearHand();
				gameplay.append("Dealing cards for house\n");
				d.deal();
				d.deal();
				gDeckSize.setText("Deck: " + d.getdecksize());
				p2_sum = d.getsum();
				while(p2_sum <= p1_sum)
				{
					d.deal();
					p2_sum = d.getsum();
				}
				if(p1_sum > 21) winner = 2;
				else if(p2_sum > 21) winner = 1;
				else if(p1_sum > p2_sum) winner = 1;
				else if(p2_sum > p1_sum) winner = 2;
				else winner = 3;
				gameplay.append("House: " + d.printhand() + "\n");
			}
			if(winner == 1) 
			{
				gameplay.append("Player wins\n");
				balance += Double.parseDouble(bet_amount.getText()) * 0.5;
			}
			else if(winner ==2)
			{
				gameplay.append("House wins\n");
				balance -= Double.parseDouble(bet_amount.getText());
			}
			else
			{
				gameplay.append("Tie game\n");
			}
			winner = 0;
			start_game.setEnabled(true);
			bet_amount.setEditable(true);
			hit.setEnabled(true);
			gDeckSize.setText("Deck: " + d.getdecksize());
			gBalance.setText("Balance: " + balance);
			gameplay.setCaretPosition(gameplay.getDocument().getLength());
		}
	}  
	
	public casinogui()
	{
		super();
		d = new deck();
		balance = 750.00;
		JPanel panel_north = new JPanel();
		JPanel panel_south = new JPanel();
		//panel_south.setLayout(new BorderLayout());
		
		gBalance = new JLabel("Balance: " + balance);
		gCount = new JLabel("Count: ");
		gSum = new JLabel("Hand sum: ");
		gDeckSize = new JLabel("Deck: ");
		
		gameplay = new JTextArea(30, 30);
		JScrollPane jscroll = new JScrollPane(gameplay);
		bet_amount = new JTextField(10);
		start_game = new JButton("Bet");
		hit = new JButton("Hit");
		deal = new JButton("Stick");
		StartListener sListen = new StartListener();
		start_game.addActionListener(sListen);
		HitListener hListen = new HitListener();
		hit.addActionListener(hListen);
		StickListener stListen = new StickListener();
		deal.addActionListener(stListen);
		panel_north.add(gBalance);
		panel_north.add(gSum);
		panel_north.add(gDeckSize);
		panel_north.add(gCount);
		panel_south.add(bet_amount);
		panel_south.add(start_game);
		panel_south.add(hit);
		panel_south.add(deal);
		add(panel_north, BorderLayout.NORTH);
		add(panel_south, BorderLayout.SOUTH);
		add(jscroll, BorderLayout.CENTER);
		gameplay.setText("Place bet amount and start dealing");
	}
}

class cardcounter
{

public static void main(String [] args)
{
	casinogui g = new casinogui();
	g.setSize(800, 600);
	g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	g.setTitle("Card Counter v1.0 Author: Spencer Brydges");
	g.setVisible(true);
}

}
