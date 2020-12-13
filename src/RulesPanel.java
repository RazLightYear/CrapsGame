import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;


/**
 * This class displays the Rules panel where the rules displayed in a text area
 * Here you can read to game rules and go back to the main menu
 * This class extends JPanel class and implements the ViewOnOff and ActionListener interfaces methods
 */
public class RulesPanel extends JPanel implements ViewOnOff, ActionListener
{
	private JTextArea RulesText;
	private JButton RulesBack;
	private final Font f2=new Font("Arial",Font.BOLD,17);
	
	/**
	 * The class Constructor which invoke the different methods for building the panel 
	 * and its components
	 */
	public RulesPanel()
	{
		createComponents();
		setLayoutManager();
		addComponents();
		positionComponents();
		AddRulesText();
		AddActionEvents();
	}
	
	/**
	 * Method to open and read the Rules.txt file in the project`s SRC folder and show the text in the 
	 * text area in the panel
	 */
	private void AddRulesText()
	{
		try
		{
		    File RulesFile = new File("Rules.txt");
		    Scanner myReader = new Scanner(RulesFile);
		    while (myReader.hasNextLine()) 
		    {
		       String data = myReader.nextLine();
		       RulesText.setText(RulesText.getText()+data+"\n");
		    }
		    myReader.close();
		 } 
		catch (FileNotFoundException e) 
		{
		    System.err.println("File Not Found");
		    e.printStackTrace();
		}
	}

	/**
	 * Method for adding the components to the panel
	 */	
	private void addComponents()
	{
		this.add(RulesText);
		this.add(RulesBack);
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
		RulesText=new JTextArea();
		RulesBack=new JButton("Back");
	}
	
	/**
	 * Method for positioning the components in the panel and
	 * setting different settings of each component like Font, BackGround color etc.
	 */
	private void positionComponents() 
	{
		this.RulesText.setEditable(false);
		this.RulesText.setFont(f2);
		this.RulesText.setBounds(40, 30, 400, 300);
		this.RulesText.setBackground(new Color(204,204,204));
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		this.RulesText.setBorder(border);			
		RulesBack.setBounds(190,340,120,30);
		RulesBack.setFont(f2);
	}
	
	/**
	 * Method to add ActionListeners to the buttons
	 */
	private void AddActionEvents() 
	{
		RulesBack.addActionListener(this);
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
		if(ev.getSource()==RulesBack)
		{
			Manager.ShowMenuPanel();
		}
	}
	
}