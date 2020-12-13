import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class displays the Menu panel where main page and options are displayed
 * Here you can choose to start the game, view the rules or exit the game.
 * This class extends JPanel class and implements the ViewOnOff and ActionListener interfaces methods
 */
public class MenuPanel extends JPanel implements ViewOnOff, ActionListener
{
	private JButton Start,Exit,Rules;
	private JLabel Headline,IconLabelTopLeft,IconLabelBottomRight;
	private ImageIcon Icon;
	private final Font f1=new Font("Arial",Font.BOLD,35);
	private final Font f2=new Font("Arial",Font.BOLD,17);
	
	/**
	 * The class Constructor which invoke the different methods for building the panel 
	 * and its components
	 */
	public MenuPanel()
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
		this.add(this.Start);
		this.add(this.Exit);
		this.add(this.Rules);
		this.add(this.Headline);
		this.add(this.IconLabelTopLeft);
		this.add(this.IconLabelBottomRight);
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
		this.Start=new JButton("Start");
		this.Exit=new JButton("Exit");
		this.Rules=new JButton("Rules");
		this.Headline=new JLabel("Craps!");
		ImageIcon Original=new ImageIcon("dices.png");
		this.Icon=new ImageIcon(Original.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)); // Resizing The Original Image
		// Top Left Icon //
		this.IconLabelTopLeft=new JLabel(Icon);				
		// Bottom Right Icon //
		this.IconLabelBottomRight=new JLabel(Icon);		
	}
	
	/**
	 * Method for positioning the components in the panel and
	 * setting different settings of each component like Font, BackGround color etc.
	 */
	private void positionComponents() 
	{
		this.Headline.setFont(f1);
		this.Headline.setBounds(170,60, 200, 50);
		this.Headline.setForeground(Color.black);
		//
		this.Start.setBounds(Headline.getX()+20, Headline.getY()+Headline.getHeight()*2, 90, 30);
		this.Start.setFont(f2);		
		//
		this.Exit.setBounds(Start.getX(), Start.getY()+Start.getHeight()*2, 90, 30);
		this.Exit.setFont(f2);
		//		
		this.Rules.setBounds(getWidth(),getHeight(),90,30);
		this.Rules.setBounds(Exit.getX(), Exit.getY()+Exit.getHeight()*2, 90, 30);
		this.Rules.setFont(f2);
		//		
		this.IconLabelTopLeft.setBounds(Headline.getX()-Icon.getIconWidth()-60, Headline.getY()-40, Icon.getIconWidth(), Icon.getIconHeight());
		this.IconLabelBottomRight.setBounds(Rules.getX()+Rules.getWidth()+80, Rules.getY()+60, Icon.getIconWidth(), Icon.getIconHeight());
	}
	
	/**
	 * Method to add ActionListeners to the buttons
	 */
	private void AddActionEvents() 
	{
		this.Start.addActionListener(this);
		this.Exit.addActionListener(this);
		this.Rules.addActionListener(this);
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
		if(ev.getSource()==Start)
		{
			Manager.ShowGamePanel();
		}
		
		else if(ev.getSource()==Exit)
		{
			if (JOptionPane.showConfirmDialog( this,"Exit Craps?","Warning", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)	            
	            System.exit(0);
		}
		
		else if(ev.getSource()==Rules)
		{
			Manager.ShowRulesPanel();
		}
		
	}


	
}