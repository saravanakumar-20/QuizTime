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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.*;
import java.util.Date;
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
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				if(JOptionPane.showConfirmDialog(new JFrame(),"Do you really want to exit")==JOptionPane.YES_OPTION)
				{
				    System.exit(0);
				}
				else
				{
					new MainFrame().setVisible(true);
				}
			}
		});
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
		display=new JLabel("       QUIZ TIME ,explore yourself");
		tools=Toolkit.getDefaultToolkit();
		d=tools.getScreenSize();
		setSize(d.width,d.height);
		setResizable(true);
		System.out.println(d.width+" "+d.height);
		p1=new JPanel(new GridLayout(3,1)
		{
			public void paintComponent(Graphics g)
			{
				ImageIcon icon=new ImageIcon("book.jpg");
		        Image image = icon.getImage(); 
				g.drawImage(image,0,0,null);
			}
		});
	    icon = new ImageIcon("book.jpg");
		imageLabel = new JLabel(icon);
		
    }
	public void designComponent()
	{
		display.setFont(new Font("",Font.ITALIC,50));
		ImageIcon icon=new ImageIcon("book3.jpg");
		Image image = icon.getImage();
		display.setIcon(icon);
		test.setBackground(new Color(0.3f,0.5f,0.7f));
		practice.setBackground(new Color(0.6f,0.2f,0.8f));
		test.setFont(new Font("",Font.BOLD,50));
		quit.setFont(new Font("",Font.BOLD,50));
		quit.setBackground(new Color(0.8f,0.4f,0.2f));
		practice.setFont(new Font("",Font.BOLD,50));
		p1.add(display);
		p1.add(test);
		p1.add(quit);
		add(p1);
	}
	
}
class Registration extends JFrame 
{
	Dimension dim;
	Toolkit tools;
	JButton submit;
	JButton previous;
	JTextField name,qual,email;
	JPasswordField pass;
	JLabel l1,l2,l3,l4;
	JPanel p,p2;
	JButton already;
	MyComponent c;
	public Registration()
	{
		tools = Toolkit.getDefaultToolkit();
		dim=tools.getScreenSize();
		setLocation((dim.width/2)-(150),(dim.height/2)-(150));
		setResizable(false);
		setTitle("Registration");
		setSize(444,344);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				dispose();
			}
		});
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
		previous=new JButton("<-BACK");
		l1=new JLabel("Enter Name: ");
		l2=new JLabel("Enter Email Id: ");
		l3=new JLabel("Enter Qualification: ");
		l4=new JLabel("Enter Password: ");
		p = new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				ImageIcon icon = new ImageIcon("book1.jpg");
				Image image=icon.getImage();
				g.drawImage(image,0,0,null);
			}
		};
		c = new MyComponent();
	}
	public void designComponent()
	{
	   l1.setFont(new Font("",Font.BOLD,20));
	   l2.setFont(new Font("",Font.BOLD,20));
	   l3.setFont(new Font("",Font.BOLD,20));
	   l4.setFont(new Font("",Font.BOLD,20));
	   l1.setForeground(Color.pink);
	   l2.setForeground(Color.pink);
	   l3.setForeground(Color.pink);
	   l4.setForeground(Color.pink);
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
			   Registration t = new Registration();
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
					   SubjectSelectorScreen s = new SubjectSelectorScreen(c.getEmail());
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
	Dimension dim;
	Toolkit toolkit;
	public Login()
	{
		toolkit=Toolkit.getDefaultToolkit();
		dim=toolkit.getScreenSize();
		setLocation((dim.width/2)-(150),(dim.height/2)-(150));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(444,300);
		setResizable(false);
		setTitle("Login");
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				dispose();
			}
		});
		buildComponent();
		designComponent();
	}
	public void buildComponent()
	{
		pass=new JPasswordField(30);
		email=new JTextField(30);
		name=new JTextField(30);
		submit=new JButton("login");
		back=new JButton("<-back");
		p=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				ImageIcon icon=new ImageIcon("boo2.jpg");
				Image image=icon.getImage();
				g.drawImage(image,0,0,null);
			}
		};
		l1=new JLabel("Enter EmailId:");
		l2=new JLabel("Enter UserName:");
		l3=new JLabel("Enter Password");
	}
	public void designComponent()
	{
		l2.setFont(new Font("",Font.BOLD,15));
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
				   System.out.println(can.getEmail()+" :Login");
				   SubjectSelectorScreen s= new SubjectSelectorScreen(can.getEmail());
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
class PathToScoreBoard extends JFrame
{
	JButton clickMe,close;
	JPanel p;
	int score=0;
	Toolkit toolkit;
	Dimension dim;
	String candidateEmail;
	JButton candidateDetail;
	String corrected[]=new String[10];
	String selected[]=new String[10];
	Candidate candidate;
	String subject;
	public PathToScoreBoard(String email,String[] correctAnswers,String sub)
	{
		System.out.println(email+":pathToScoreBoard");
		subject=sub;
		candidateEmail=email;
		for(int i=0;i<10;i++)
		{
			corrected[i]=correctAnswers[i];
		}
		setSize(300,300);
		setResizable(false);
		toolkit=Toolkit.getDefaultToolkit();
		dim=toolkit.getScreenSize();
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation((dim.width/2)-(150),(dim.height/2)-(150));
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				dispose();
			}
		});
		buildComponent();
		designComponent();
	}
	public void buildComponent()
	{
		p=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				ImageIcon icon=new ImageIcon("book11.jpg");
				Image image=icon.getImage();
				g.drawImage(image,0,0,null);
			}
		};
		candidateDetail=new JButton("Click here to know about the top scorer");
		clickMe=new JButton("click here to get the score card");
		close=new JButton("close");
	}
	public void designComponent()
	{
		p.add(clickMe,BorderLayout.CENTER);
		p.add(candidateDetail,BorderLayout.SOUTH);
		p.add(close,BorderLayout.SOUTH);
		clickMe.setBackground(Color.white);
		candidateDetail.setBackground(Color.white);
		close.setBackground(Color.white);
		add(p);
		close.addActionListener((ae)->
		{
			dispose();
		});
		candidateDetail.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				try
			    {
				  DBHandler db=new DBHandler();
				  System.out.println(candidateEmail+":insertScore");
				  boolean x=db.insertScore(candidateEmail,score,subject);
				  if(x==true)
				   System.out.println("ScoreInserted");
				  else
				   System.out.println("not inserted");
			    }
			    catch(Exception e)
			    {
				  System.out.println(e.getClass().getName());
			    }
				new HighScorer(subject);
		  }
		});
		clickMe.addActionListener((ae)->
		{
			try
			{
			  DBHandler db=new DBHandler();
			  for(int i=0;i<10;i++)
			  {
				selected[i]=db.getAns(candidateEmail,i+1);
				System.out.println("selected: "+selected[i]);
				System.out.println("actual: "+corrected[i]);
				boolean bool=selected[i].equals(corrected[i]);
				System.out.println(bool);
				if(bool)
				{
				   System.out.println("incremented");
				   score++;
				}
			  }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			ScoreBoard board = new ScoreBoard(selected,corrected,score);
			board.setVisible(true);
		});
		
	}
}

class HighScorer extends JFrame
{
	JLabel title;
	Toolkit tools;
	Dimension dim;
	JPanel p;
	String subject;
	Candidate candidate;
	JLabel name,qual;
	JTextField namet,qualt;
	JButton back;
	public HighScorer(String sub)
	{
		subject=sub;
		setVisible(true);
		tools=Toolkit.getDefaultToolkit();
		dim=tools.getScreenSize();
		setLocation((dim.width/2)-(150),(dim.height/2)-(150));
		setLocation(150,20);
		setTitle("HighScorer");
		setSize(400,400);
		setLocation((dim.width/2)-(150),(dim.height/2)-(150));
		buildComponent(sub);
		designComponent();
	}
	public void setCandidate()
	{
		try
		{
			DBHandler db=new DBHandler();
			String email=db.retrieveHighScorer(subject);
			candidate=db.retrieveCandidate(email);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void buildComponent(String sub)
	{
		p=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				ImageIcon icon = new ImageIcon("high.jpg");
				Image image=icon.getImage();
				g.drawImage(image,0,0,null);
			}
		};
		back=new JButton("<--back");
		namet=new JTextField(20);
		qualt=new JTextField(20);
		title=new JLabel("High Scorer In "+sub);
		name=new JLabel("Name:");
		qual=new JLabel("Qualification:");
	}
	public void designComponent()
	{
		setCandidate();
		title.setFont(new Font("",Font.BOLD,30));
		title.setForeground(Color.white);
		name.setFont(new Font("",Font.BOLD,25));
		qual.setFont(new Font("",Font.BOLD,25));
		name.setForeground(Color.white);
		qual.setForeground(Color.white);
		namet.setText(candidate.getName());
		qualt.setText(candidate.getQualification());
		namet.setFont(new Font("",Font.BOLD,20));
		qualt.setFont(new Font("",Font.BOLD,20));
		namet.setEditable(false);
		qualt.setEditable(false);
		back.setBackground(Color.orange);
		p.add(title);
		p.add(name);
		p.add(namet);
		p.add(qual);
		p.add(qualt);
		p.add(back);
		add(p);
		back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				setVisible(false);
			}
		});
	}
		
}
class SubjectSelectorScreen extends JFrame
{
	TestQuestion array[]=new TestQuestion[10];
	static int count=0;
	int questionCount;
	String options[]=new String[4];
	String candidateEmail;
	JButton general,quantitative,english,exit;
	JPanel p;
	Toolkit tools;
	String question;
	TestQuestion ques;
	Dimension dim;
	public SubjectSelectorScreen(String email)
	{
		System.out.println(email+":subjectSelector");
		candidateEmail=email;
		tools=Toolkit.getDefaultToolkit();
		dim=tools.getScreenSize();
		setSize(dim.width,dim.height);
		setResizable(false);
		setTitle("Subject Selection Selection");
		buildComponent();
		designComponent();
		english.addActionListener((ae)->
		{
			count=(count+1)%4;
			TestQuestion.attendCount=1;
			String fileName="e"+(count+1)+".txt";
			String fileAnsName="e"+(count+1)+"ans.txt";
			questionCount=1;
			String correctAnswers[]=new String[10];
			try
			{
			  File file1=new File(fileAnsName);
			  Scanner read1=new Scanner(file1);
			  int i=0;
			  while(read1.hasNextLine())
			  {
		         correctAnswers[i]=read1.nextLine();
				 i++;
			  }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
				File file2=new File(fileName);
				Scanner read2=new Scanner(file2);
				while(read2.hasNextLine())
				{
					question=read2.nextLine();
					for(int i=0;i<4;i++)
					   options[i]=read2.nextLine();
					ques=new TestQuestion(question,options,candidateEmail,questionCount);
					questionCount++;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			Thread thread1= new Thread(new Runnable(){
				public void run()
				{

					String min="";
		            String sec="";
		            int count=0;
					while(count!=301)
					{
						if(TestQuestion.attendCount%11==0)
						{
						  break;
						}
						min=""+(count/60);
			            sec=""+(count%60);
			            MyTimer timer = new MyTimer(min,sec);
			            try
			            {
			              Thread.sleep(1000);
			            }
			            catch(Exception e)
			            {
				          System.out.println(e.getClass().getName());
			            }
			            timer.dispose();
			            count++;
					}
					if(TestQuestion.attendCount%11!=0)
					{
					  for(int i=0;i<10;i++)
					  {
						array[i].dispose();
					  }
					}
					PathToScoreBoard path=new PathToScoreBoard(candidateEmail,correctAnswers,"english");
			        path.setVisible(true);
				}
			   });
		    thread1.start();
		});
		general.addActionListener((ae)->
		{
			count=(count+1)%4;
			TestQuestion.attendCount=1;
			String fileName="g"+(count+1)+".txt";
			String fileAnsName="g"+(count+1)+"ans.txt";
			questionCount=1;
			String correctAnswers[]=new String[10];
			ScheduledExecutorService service=Executors.newSingleThreadScheduledExecutor();
			try
			{
			  File file1=new File(fileAnsName);
			  Scanner read1=new Scanner(file1);
			  int i=0;
			  while(read1.hasNextLine())
			  {
				  correctAnswers[i]=read1.nextLine();
				  i++;
			  }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try
			{
			 File file2=new File(fileName);
			 Scanner read2=new Scanner(file2);
			 while(read2.hasNextLine())
			 {
				question=read2.nextLine();
				for(int i=0;i<4;i++)
				{
					options[i]=read2.nextLine();
				}
				ques=new TestQuestion(question,options,candidateEmail,questionCount);
				ques.setVisible(true);
				service.schedule(new Runnable()
				{
				  public void run()
				  {
					question=read2.nextLine();
					for(int i=0;i<4;i++)
					{
						options[i]=read2.nextLine();
					}
					ques = new TestQuestion(question,options,candidateEmail,questionCount);
					ques.setVisible(true);
				  }	
				},150,TimeUnit.SECONDS);
				questionCount++;
			 }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			Thread thread1= new Thread(new Runnable(){
				public void run()
				{
					String min="";
		            String sec="";
		            int count=0;
					while(count!=301)
					{
						if(TestQuestion.attendCount%11==0)
						  break;
						min=""+(count/60);
			            sec=""+(count%60);
			            MyTimer timer = new MyTimer(min,sec);
			            try
			            {
			              Thread.sleep(1000);
			            }
			            catch(Exception e)
			            {
				          System.out.println(e.getClass().getName());
			            }
			            timer.dispose();
			            count++;
					}
					if(TestQuestion.attendCount%11!=0)
					{
					  for(int i=0;i<10;i++)
					  {
						array[i].dispose();
					  }
					}
					PathToScoreBoard path=new PathToScoreBoard(candidateEmail,correctAnswers,"general");
			        path.setVisible(true);
				}
			   });
		    thread1.start();
		});
		quantitative.addActionListener((ae)->
		{
			TestQuestion.attendCount=1;
			count=(count+1)%4;
			questionCount=1;
			String fileName="m"+(count+1)+".txt";
			String answerFileName="m"+(count+1)+"ans.txt";
		    int arrayIndex=0;
			String correctAnswers[]=new String[10];
			try
			{
				File file1=new File(answerFileName);
				Scanner read1=new Scanner(file1);
				int index=0;
				while(read1.hasNextLine())
				{
				  correctAnswers[index++]=read1.nextLine();
				}
				File file2 = new File(fileName);
				Scanner read2 = new Scanner(file2);
				while(read2.hasNextLine())
				{
					question=read2.nextLine();
					System.out.println(question);
					for(int i=0;i<4;i++)
					{
						options[i]=read2.nextLine();
					}
					System.out.println(questionCount);
					ques=new TestQuestion(question,options,candidateEmail,questionCount);
					array[arrayIndex++]=ques;
					questionCount++;
				}
			}
			catch(Exception e)
		    {
				System.out.println(e.getClass().getName());
			}
			Thread thread1= new Thread(new Runnable(){
				public void run()
				{
                    String min="";
		            String sec="";
		            int count=0;
					while(count!=301)
					{
						if(TestQuestion.attendCount%11==0)
					       break;
						min=""+(count/60);
			            sec=""+(count%60);
			            MyTimer timer = new MyTimer(min,sec);
			            try
			            {
			              Thread.sleep(900);
			            }
			            catch(Exception e)
			            {
				          System.out.println(e.getClass().getName());
			            }
			            timer.dispose();
			            count++;
					}
					if(TestQuestion.attendCount%11!=0)
					{
					   for(int i=0;i<10;i++)
					   {
						array[i].dispose();
					   }
					}
					PathToScoreBoard path=new PathToScoreBoard(candidateEmail,correctAnswers,"maths");
				    path.setVisible(true);
				}
			   });
		    thread1.start();
			
		});
        exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if(JOptionPane.showConfirmDialog(new SubjectSelectorScreen(""),"Do You Really Want To Exit")==JOptionPane.YES_OPTION)
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
	Toolkit tools;
	Dimension dim;
	public TermsAndConditionsScreen()
	{
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,720);
		tools=Toolkit.getDefaultToolkit();
		dim=tools.getScreenSize();
		setResizable(true);
		buildComponent();
		designComponent();
	}
	public void buildComponent()
	{
		area=new JTextArea(20,20);
		check=new JCheckBox("I have carefully read the instructions");
		panel=new JPanel()
		{
			public void paintComponent(Graphics g)
			{
				ImageIcon icon = new ImageIcon("book.jpg");
				Image image=icon.getImage();
				g.drawImage(image,0,0,null);
			}
		};
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
            Registration t=new Registration();
			t.setVisible(true);
		});
	}
}
public class Quiz
{
	public static void main(String[] args)
	{
		
		MainFrame f = new MainFrame();
		f.setVisible(true);
	}
}