import java.util.Scanner;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.*;
class MainFrame extends JFrame
{
	JButton practice;
	JButton test;
	JButton quit;
	JPanel p1,p2;
	JTextField ta;
	Toolkit tools;
	Dimension d;
	JLabel display;
	MyComponent c;
	ImageIcon icon;
	Image image;
	Image newImg;
	ImageIcon  im;
	JLabel imageLabel;
	public MainFrame()
	{
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Quiz Time");
		buildComponent();
		designComponent();
		test.addActionListener(new ActionListener()
	    {
		  public void actionPerformed(ActionEvent ae)
		  {
			TermsAndConditionsScreen termsScreen = new TermsAndConditionsScreen();
			termsScreen.setVisible(true);
		  }
	    });
		quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(JOptionPane.showConfirmDialog(new JFrame(), "Do You Really Want To Quit Quiz Time")==JOptionPane.YES_OPTION)
				{
				     System.exit(1);
				}
			}
		});
	}
	public void buildComponent()
	{
		c=new MyComponent();
	    practice=new JButton("Practice Session");
        test=new JButton("Take Test");
		quit=new JButton("Quit");
		display=new JLabel("                       QUIZ TIME ,explore yourself");
		tools=Toolkit.getDefaultToolkit();
		d=tools.getScreenSize();
		setSize(d.width,d.height);
		setResizable(true);
		System.out.println(d.width+" "+d.height);
		p1=new JPanel(new GridLayout(4,1));
	    icon = new ImageIcon("book.jpg");
		imageLabel = new JLabel(icon);
		
    }
	public void designComponent()
	{
		display.setFont(new Font("",Font.ITALIC,50));
		test.setBackground(new Color(0.3f,0.5f,0.7f));
		practice.setBackground(new Color(0.6f,0.2f,0.8f));
		test.setFont(new Font("",Font.BOLD,50));
		quit.setFont(new Font("",Font.BOLD,50));
		quit.setBackground(new Color(0.8f,0.4f,0.2f));
		practice.setFont(new Font("",Font.BOLD,50));
		p1.add(display);
		p1.add(practice);
		p1.add(test);
		p1.add(quit);
		add(p1);
	}
	
}
class TakeTest extends JFrame 
{
	Dimension d;
	Toolkit tools;
	JButton submit;
	JButton previous;
	JTextField name,qual,email;
	JPasswordField pass;
	JLabel l1,l2,l3,l4;
	JPanel p,p2;
	JButton already;
	MyComponent c;
	public TakeTest()
	{
		tools = Toolkit.getDefaultToolkit();
		d=tools.getScreenSize();
		setResizable(false);
		setTitle("Registration");
		setSize(444,344);
		buildComponent();
		designComponent();
		
	}
	public void buildComponent()
	{
		already=new JButton("Already Having An Account?");
		name = new JTextField(30);
		qual=new JTextField(25);
		email=new JTextField(30);
		pass=new JPasswordField(30);
		submit=new JButton("SUBMIT");
		previous=new JButton("BACK");
		l1=new JLabel("Enter Name: ");
		l2=new JLabel("Enter Email Id: ");
		l3=new JLabel("Enter Qualification: ");
		l4=new JLabel("Enter Password: ");
		p = new JPanel();
		c = new MyComponent();
	}
	public void designComponent()
	{
	   l1.setFont(new Font("",Font.BOLD,20));
	   l2.setFont(new Font("",Font.BOLD,20));
	   l3.setFont(new Font("",Font.BOLD,20));
	   l4.setFont(new Font("",Font.BOLD,20));
	   submit.setEnabled(true);
	   already.addActionListener((ae)->
	   {
		   Login l=new Login();
		   setVisible(false);
		   l.setVisible(true);
	   });
	   previous.addActionListener(new ActionListener()
	   {
		   public void actionPerformed(ActionEvent ae)
		   {
			   setVisible(false);
		   }
	   });
	   submit.addActionListener(new ActionListener()
	   {
		   public void actionPerformed(ActionEvent ae)
		   {
			   TakeTest t = new TakeTest();
			   boolean var= (name.getText().equals("")) || (qual.getText().equals("")) || (email.getText().equals("") ) || (pass.getText().equals(""));
			   if(var==false)
			   {
				  try
				  {
				   DBHandler db = new DBHandler();
				   Candidate c = new Candidate(name.getText(),qual.getText(),email.getText(),pass.getText());
				   int res = db.insertCandidate(c);
				   if(res > 0)
				   {
				       JOptionPane.showMessageDialog(t,"registration successful");
					   name.setText(" "); email.setText(" "); qual.setText(" ");
					   setVisible(false);
					   LevelSelectorScreen s = new LevelSelectorScreen();
					   s.setVisible(true);
				   }
				   else if(res<0)
				   {
				       JOptionPane.showMessageDialog(t,"insertion unsuccessful","data base problem",JOptionPane.ERROR_MESSAGE);
				   }
				   else
				   {
					   JOptionPane.showMessageDialog(t,"email id already taken","insertion failed",JOptionPane.INFORMATION_MESSAGE);
				   }
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				   
			   }
			   else 
			   {
				   JOptionPane.showMessageDialog(t,"Enter Essential Details","Insufficient data",JOptionPane.ERROR_MESSAGE);
			   }
			       
		   }
	   });
	   p.add(l1,BorderLayout.WEST);p.add(name,BorderLayout.EAST);
	   p.add(l2,BorderLayout.WEST);p.add(email,BorderLayout.EAST);
	   p.add(l3,BorderLayout.WEST);p.add(qual,BorderLayout.EAST);
	   p.add(l4,BorderLayout.WEST);p.add(pass,BorderLayout.EAST);
	   p.add(submit,BorderLayout.CENTER);p.add(previous);p.add(already);
	   add(p);
	}
}
class Login extends JFrame
{
	JPasswordField pass;
	JTextField email,name;
	JLabel l1,l2,l3;
	JButton submit,back;
	JPanel p;
	public Login()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(444,300);
		setResizable(false);
		setTitle("Login");
		buildComponent();
		designComponent();
	}
	public void buildComponent()
	{
		pass=new JPasswordField(30);
		email=new JTextField(30);
		name=new JTextField(30);
		submit=new JButton("login");
		back=new JButton("back");
		p=new JPanel();
		l1=new JLabel("Enter EmailId:");
		l2=new JLabel("Enter UserName:");
		l3=new JLabel("Enter Password");
	}
	public void designComponent()
	{
		l2.setFont(new Font(" ",Font.BOLD,15));
		l1.setFont(new Font("",Font.BOLD,15));
		l3.setFont(new Font("",Font.BOLD,15));
		p.add(l2,BorderLayout.WEST);p.add(name,BorderLayout.EAST);
		p.add(l1,BorderLayout.WEST);p.add(email,BorderLayout.EAST);
		p.add(l3,BorderLayout.WEST);p.add(pass,BorderLayout.EAST);
		p.add(submit);p.add(back);
		add(p);
		back.addActionListener((ae)->
		{
			setVisible(false);
		});
		submit.addActionListener((ae)->
		{
			boolean boolVar=pass.getText().equals("") || name.getText().equals("") || email.getText().equals("");
			if(boolVar==true)
			   JOptionPane.showMessageDialog(new JFrame(),"Enter Essential Data","Insufficient Data",JOptionPane.ERROR_MESSAGE);
			else
			{
			  try
			  {
				DBHandler db = new DBHandler();
				Candidate can=new Candidate(name.getText(),email.getText(),pass.getText());
				if(db.searchCandidate(can))
				{
				   JOptionPane.showMessageDialog(new JFrame(),"Login Successful");
				   setVisible(false);
				   SubjectSelectorScreen s= new SubjectSelectorScreen();
				   s.setVisible(true);
				}
				else
				   JOptionPane.showMessageDialog(new Login(),"Enter The Correct Email Id And Password","Login Unsuccessful",JOptionPane.ERROR_MESSAGE);
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
			}
		});
		
	}
}
class MyComponent extends JComponent
{
	ImageIcon icon = new ImageIcon("download.jpg");
	Image image = icon.getImage();
	public void paintComponent(Graphics g)
	{
		g.drawImage(image,200,200,200,200,this);
	}
}
class SubjectSelectorScreen extends JFrame
{
	static int cnt=0;
	JButton general,quantitative,english,exit;
	JPanel p;
	Toolkit tools;
	Dimension dim;
	public SubjectSelectorScreen()
	{
		tools=Toolkit.getDefaultToolkit();
		dim=tools.getScreenSize();
		setSize(dim.width,dim.height);
		setResizable(false);
		setTitle("Subject Selection Selection");
		buildComponent();
		designComponent();
		quantitative.addActionListener((ae)->
		{
			System.out.println("quantitative Test");
		});
        exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(JOptionPane.showConfirmDialog(new LevelSelectorScreen(),"Do You Really Want To Exit")==JOptionPane.YES_OPTION)
				    setVisible(false);
			}
		});
	}
	public void buildComponent()
	{
		general=new JButton("General Knowledge");
		quantitative=new JButton("Quantitative Mathematics");	
		english=new JButton("English");
		p=new JPanel(new GridLayout(4,1));
		exit=new JButton("Exit");
	}
	public void designComponent()
	{
		general.setFont(new Font("",Font.CENTER_BASELINE,50));
		quantitative.setFont(new Font("",Font.CENTER_BASELINE,50));
		english.setFont(new Font("",Font.CENTER_BASELINE,50));
		exit.setFont(new Font("",Font.CENTER_BASELINE,50));
		Color myColour2 = new Color(0.9f,0.1f,0.2f);
		Color myColour3 = new Color(0.1f,0.8f,0.1f);
		general.setBackground(myColour3);
		quantitative.setBackground(myColour2);
		Color myColour1 = new Color(0.1f,0.1f,0.9f);
		exit.setBackground(myColour1);
		english.setBackground(Color.orange);
		p.add(general);
		p.add(quantitative);
		p.add(english);
		p.add(exit);
		add(p);
	}
	
}
class TermsAndConditionsScreen extends JFrame
{
	JScrollPane pane;
	JTextArea area;
	JPanel panel;
	JButton next;
	JCheckBox check;
	public TermsAndConditionsScreen()
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,600);
		setLocation(0,0);
		setResizable(true);
		buildComponent();
		designComponent();
	}
	public void buildComponent()
	{
		area=new JTextArea(20,20);
		check=new JCheckBox("I have carefully read the instructions");
		panel=new JPanel();
		next=new JButton("next");
		next.setEnabled(false);
	}
	public void designComponent()
	{
		panel.add(area);
		panel.add(check);
		panel.add(next);
		try
		{
			File file=new File("conditions.txt");
			Scanner read = new Scanner(file);
			while(read.hasNextLine())
			{
				area.append(read.nextLine());
				area.append("\n\n");
			}
			read.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		area.setEditable(false);
		add(panel);
		check.addItemListener(new ItemListener(){
		 public void itemStateChanged(ItemEvent ie)
		{
		   if(ie.getStateChange()==ItemEvent.SELECTED)
			next.setEnabled(true);
		   else
		    next.setEnabled(false);
		   
		}
		});
		next.addActionListener((ae)->
		{ 
		    setVisible(false);
            TakeTest t=new TakeTest();
			t.setVisible(true);
		});
	}
}
public class MainFrameTest
{
	public static void main(String[] args)
	{
		
		MainFrame f = new MainFrame();
		f.setVisible(true);
	}
}