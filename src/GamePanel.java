import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

/**
 * This class displays the game panel where the game takes place.
 * Here you can place your bet and start rolling the dice
 * This class extends JPanel class and implements the ViewOnOff and ActionListener interfaces methods
 */

public class GamePanel extends JPanel implements ViewOnOff, ActionListener
{
	private JButton Roll,GameBack,PlaceBet;
	public  JTextField CurrentStatus,PointStatus,CurrentRoll,UserBet,UserCash;
	private final Font f2=new Font("Arial",Font.BOLD,17);
	private JLabel GamestatusLabel,pointStatusLabel,CurrRollLabel,UserBetLabel,UserCashLabel;
	
	/**
	 * The class Constructor which invoke the different methods for building the panel 
	 * and its components
	 */
	public GamePanel()
	{
		createComponents();
		setLayoutManager();
		addComponents();
		positionComponents();
		AddActionEvents();
	}

	/**
	 * Method for adding the components to the panel
	 */	
	private void addComponents()
	{
		this.add(this.Roll);
		this.add(this.GameBack);
		this.add(this.CurrentStatus);
		this.add(this.PointStatus);
		this.add(this.CurrentRoll);
		this.add(this.GamestatusLabel);
		this.add(this.pointStatusLabel);
		this.add(this.CurrRollLabel);
		this.add(this.PlaceBet);
		this.add(this.UserBet);
		this.add(this.UserCash);
		this.add(this.UserBetLabel);
		this.add(this.UserCashLabel);
	}

	/**
	 * Method for setting the Layout manager and background
	 */
	private void setLayoutManager() 
	{
		this.setLayout(null);
		this.setBackground(new Color(7,99,36));		
	}

	/**
	 * Method for creating the components 
	 */
	private void createComponents() 
	{
		Roll=new JButton("Roll");
		GameBack=new JButton("Back");
		CurrentStatus=new JTextField();
		PointStatus=new JTextField();
		CurrentRoll=new JTextField();
		GamestatusLabel=new JLabel("Game Status");
		pointStatusLabel=new JLabel("Your Point");
		CurrRollLabel=new JLabel("Your Current Roll");
		PlaceBet=new JButton("Set");
		UserBet=new JTextField();
		UserCash=new JTextField();
		UserBetLabel=new JLabel("Your Bet");
		UserCashLabel=new JLabel("Your Cash");
	}
	
	/**
	 * Method for positioning the components in the panel and
	 * setting different settings of each component like Font, BackGround color etc.
	 */
	private void positionComponents() 
	{
		Roll.setBounds(190, 160, 120, 30);
		Roll.setFont(f2);
		
		GameBack.setBounds(Roll.getX(), Roll.getY()+Roll.getHeight()*6, 120, 30);
		GameBack.setFont(f2);
				
		CurrentStatus.setFont(f2);
		CurrentStatus.setBackground(Color.white);
		CurrentStatus.setForeground(Color.black);
		CurrentStatus.setBounds(Roll.getX()-Roll.getWidth()-50, Roll.getY(), 140, 30);
		CurrentStatus.setEditable(false);
		
		GamestatusLabel.setFont(f2);
		GamestatusLabel.setForeground(Color.black);
		GamestatusLabel.setBounds(CurrentStatus.getX(), CurrentStatus.getY()-30, 160, 30);		

		PointStatus.setFont(f2);
		PointStatus.setBackground(Color.white);
		PointStatus.setForeground(Color.black);
		PointStatus.setBounds(Roll.getX()+Roll.getWidth()+20, Roll.getY(), 140, 30);
		PointStatus.setEditable(false);
		
		pointStatusLabel.setFont(f2);
		pointStatusLabel.setForeground(Color.black);
		pointStatusLabel.setBounds(PointStatus.getX(), PointStatus.getY()-30, 160, 30);
		
		CurrentRoll.setFont(f2);
		CurrentRoll.setBackground(Color.white);
		CurrentRoll.setForeground(Color.black);
		CurrentRoll.setBounds(Roll.getX()-20, Roll.getY()+Roll.getHeight()+30, 160, 30);
		CurrentRoll.setEditable(false);
		
		CurrRollLabel.setFont(f2);
		CurrRollLabel.setForeground(Color.black);
		CurrRollLabel.setBounds(CurrentRoll.getX(), CurrentRoll.getY()-30, 160, 30);
		
		UserCash.setFont(f2);
		UserCash.setBackground(Color.white);
		UserCash.setForeground(Color.black);
		UserCash.setBounds(CurrentStatus.getX(), CurrentStatus.getY()-130, 140, 30);
		UserCash.setEditable(false);
		
		UserBet.setFont(f2);
		UserBet.setBackground(Color.white);
		UserBet.setForeground(Color.black);
		UserBet.setBounds(UserCash.getX()+UserCash.getWidth()+20, UserCash.getY(), 140, 30);
		UserBet.setEditable(true);
		
		PlaceBet.setBounds(UserBet.getX()+UserBet.getWidth()+20, UserBet.getY(), 120, 30);
		PlaceBet.setFont(f2);
		
		UserCashLabel.setFont(f2);
		UserCashLabel.setForeground(Color.black);
		UserCashLabel.setBounds(UserCash.getX(), UserCash.getY()-30, 160, 30);
		
		UserBetLabel.setFont(f2);
		UserBetLabel.setForeground(Color.black);
		UserBetLabel.setBounds(UserBet.getX(), UserBet.getY()-30, 160, 30);		
	}

	/**
	 * Method to add ActionListeners to the buttons
	 */
	private void AddActionEvents()
	{
		Roll.addActionListener(this);
		GameBack.addActionListener(this);
		PlaceBet.addActionListener(this);
	}
	
	/**
	 * Overridden method from ViewOnOff interface that serves the simple action of 
	 * showing the panel and setting its size 
	 */
	@Override
	public void ShowPanel() 
	{
		this.setSize(500, 500);
		this.setVisible(true);
		
	}

	/**
	 * Overridden method from ViewOnOff interface that serves the simple action of 
	 * hiding the panel
	 */
	@Override
	public void HidePanel()
	{
		this.setVisible(false);
		
	}

	/**
	 * Setting the actions for each event occurred
	 * @param ev - the event that occurred
	 */
	@Override
	public void actionPerformed(ActionEvent ev) 
	{
		if(ev.getSource()==GameBack)
		{
			
			Manager.ShowMenuPanel();
		}
		
		else if(ev.getSource()==Roll)
		{
			Manager.StartRoll();
		}
		
		else if(ev.getSource()==PlaceBet)
		{
			Manager.SetBet();
		}
	}	
}