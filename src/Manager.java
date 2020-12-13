import java.awt.Dimension;
import java.awt.Toolkit;
import java.security.SecureRandom;
import javax.swing.*;

/**
 * This class acts as the manager of the whole program, by managing the different methods calls from the 
 * panels,and showing/hiding the relevant panels. This class also holds and runs the whole
 * game rules and logics.
 * This class extends JFrame and acts as the frame that holds the different panels.
 *@author Raz Ben Aderet
 */
public class Manager extends JFrame
{
	private static MenuPanel menuPanel;
	private static GamePanel gamePanel;
	private static RulesPanel rulesPanel;

	// Game Variables //
	private static final SecureRandom randomNumbers = new SecureRandom();
	private enum Status{CONTINUE,WON,LOST};
	private static final int SNAKE_EYES=2;
	private static final int TREY=3;
	private static final int SEVEN=7;
	private static final int YO_LEVEN=11;
	private static final int BOX_CARS=12;
	private static int myPoint=0;
	private static Status gameStatus;
	private static int sumOfDice=0;
	private static boolean FirstRole=true,PlayerPlacedBet=false;
	private static int UserBet;
	private static int UserCash=40;
	
	/**
	 * The class Constructor which invoke the different methods for building the frame 
	 * and its components
	 */
	public Manager()
	{			
		createPanels();
		addPanels();
		startGame();
		setGeneralFrameSettings();
	}

	/**
	 * Method for starting the whole process by showing the Menu panel as a start
	 */
	private void startGame() 
	{
		ShowMenuPanel();		
	}
	
	/**
	 * Method for creating the panels 
	 */
	private void createPanels() 
	{
		this.menuPanel=new MenuPanel();
		this.gamePanel=new GamePanel();
		this.rulesPanel=new RulesPanel();	
	}
	
	/**
	 * Method for adding the panels to the main frame
	 */
	private void addPanels() 
	{
		this.add(menuPanel);
		this.add(gamePanel);
		this.add(rulesPanel);		
		this.menuPanel.setVisible(false);
		this.gamePanel.setVisible(false);
		this.rulesPanel.setVisible(false);
	}
	
	/**
	 * Method that shows the Menu Panel and hides every other panel
	 */
	public static void ShowMenuPanel()
	{
		gamePanel.HidePanel();
		rulesPanel.HidePanel();
		menuPanel.ShowPanel();
	}
	
	/**
	 * Method that shows the Game Panel and hides every other panel
	 */
	public static void ShowGamePanel()
	{
		menuPanel.HidePanel();
		gamePanel.ShowPanel();
	}
	
	/**
	 * Method that shows the Rules Panel and hides every other panel
	 */
	public static void ShowRulesPanel()
	{
		menuPanel.HidePanel();
		rulesPanel.ShowPanel();
	}
	
	/**
	 * Method to set different settings of the frame like the size, location on screen etc.
	 */
	private void setGeneralFrameSettings() 
	{
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setVisible(true);		
	}
	
	/**
	 * Method to generate a random sum of 2 dices
	 * @return the sum of 2 random dices rolls
	 */
	public static int RollDice()
	{
		int diel1=1+randomNumbers.nextInt(6);
		int diel2=1+randomNumbers.nextInt(6);
		return diel1+diel2;
	}
	
	/**
	 * Method to set the relevant text in the Game Panel`s different text Fields
	 */
	public static void ShowStatus() 
	{
		if(gameStatus==Status.CONTINUE) // Game Should continue
		{
			gamePanel.CurrentStatus.setText("Roll Again");
			UpdateCash();
		}
		
		else // Game Won Or Lost
		{
			if(gameStatus==Status.LOST)
			{
				gamePanel.CurrentStatus.setText("Player Lost");	
				CalcCash(false);
			}
			
			else
			{
				gamePanel.CurrentStatus.setText("Player Won!");	
				CalcCash(true);
			}
			FirstRole=true;
			PlayerPlacedBet=false;
			gamePanel.UserBet.setText("");
		}
	}
	
	/**
	 * Method to determine the game status at the first Roll
	 * @param Sum the sum of the dices that was generated
	 */
	public static void DetermineStatus(int Sum)
	{
		switch(Sum)
		{
		case SEVEN:
		case YO_LEVEN:
			gameStatus=Status.WON;
			gamePanel.PointStatus.setText("");
			break;
		case SNAKE_EYES:
		case TREY:
		case BOX_CARS:
			gameStatus=Status.LOST;
			gamePanel.PointStatus.setText("");
			break;
		default:
			gameStatus=Status.CONTINUE;
			myPoint=Sum;
			FirstRole=false;
			gamePanel.PointStatus.setText(Integer.toString(myPoint));
			break;
		}
	}
	
	/**
	 * Method to start the whole process of the game. In this method the rest of the methods are called
	 * to generate the dices rolls, determine the game status , calculating the winnings and losses etc. 
	 */
	public static void StartRoll() 
	{
		if(PlayerPlacedBet==true)
		{
		sumOfDice=RollDice();
		
		if(FirstRole) // If It Is The First Roll
		{
			DetermineStatus(sumOfDice);
		}
		
		else // Not The First Roll
		{
			if(sumOfDice==myPoint)
			{
				gameStatus=Status.WON;					
			}
			else if(sumOfDice==SEVEN)
			{
				gameStatus=Status.LOST;					
			}	
		}
		ShowStatus();
		gamePanel.CurrentRoll.setText(Integer.toString(sumOfDice));
		}
		
		else
		{
			JOptionPane.showMessageDialog(null, "Please Place Your Bet First");
		}
	}	
	
	/**
	 * Method to set the user`s bet.
	 * This method also checks if the user`s input is legal and numeric, otherwise- a NumberFormatException is thrown
	 */
	public static void SetBet() 
	{
		try 
		{
		UserBet=Integer.parseInt(gamePanel.UserBet.getText());
		PlayerPlacedBet=true;
		}
		catch(NumberFormatException EXC)
		{
			System.err.println("ERROR: Input Is Not Numeric");
		}		
	}
	
	/**
	 * Method to calculate the winnings or losings - based on whether the player won or lost
	 * @param LostOrWon is true if the player won, false if the player lost
	 */
	public static void CalcCash(boolean LostOrWon)
	{
		if(LostOrWon==true) // Player Won And Gets His Bet*2
		{
			UserCash+=UserBet*2;
		}
		else // Player Lost And Loses His Bet 
		{
			UserCash-=UserBet;
		}

		UpdateCash();
	}
	
	/**
	 * Method to show the amount of cash the user has, in the Game Panel relevant text field
	 */
	public static void UpdateCash()
	{		
		gamePanel.UserCash.setText(String.valueOf(UserCash)+"$");
	}
}