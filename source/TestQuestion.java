//package Questions;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
class TestQuestion extends JFrame implements ActionListener 
{
	static int count=0;
	String message;
	String options[]=new String[4];
	private String selected;
	String email;
	static int attendCount=1;
	int questionNo;
	JLabel questionLabel;
	JButton submit;
	JPanel p;
	ButtonGroup gp;
	TimerTest timer;
	Dimension dim;
	Toolkit tools;
	JRadioButton optA,optB,optC,optD;
	public TestQuestion(String message,String[] options,String email,int no)
	{
		tools=Toolkit.getDefaultToolkit();
		dim=tools.getScreenSize();
		System.out.println("constructor: "+no);
		this.email=email;
		this.questionNo=no;
		this.message=message;
		for(int i=0;i<options.length;i++)
		   this.options[i]=options[i];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buildComponent();
		designComponent();
		setSize(1000,700);
		setResizable(true);
		optA.addActionListener(this);
		optB.addActionListener(this);
		optC.addActionListener(this);
		optD.addActionListener(this);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent windowEvent)
			{
				attendCount+=1;
			}
		});
		submit.addActionListener((ae)->
		{
			count=count+1;
			attendCount+=1;
			ButtonModel bm=gp.getSelection();
		    String selection=bm.getActionCommand();
			try
			{
			  DBHandler db=new DBHandler();
			  db=new DBHandler();
			  System.out.println(questionNo);
			  db.setAnswer(email,questionNo,selection);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			dispose();
		});
		setVisible(true);
	}
	public void setSelected(String selected)
	{
		this.selected=selected;
	}
	public String getSelected()
	{
		return selected;
	}
	public void buildComponent()
	{
		p=new JPanel(new GridLayout(6,1))
		{
			public void paintComponent(Graphics g)
			{
			  ImageIcon icon = new ImageIcon("book33.jpg");
			  Image image=icon.getImage();
			  g.drawImage(image,0,0,null);
			}
			
		};
		questionLabel=new JLabel(message);
		gp=new ButtonGroup();
		optA=new JRadioButton();
		optB=new JRadioButton();
		optC=new JRadioButton();
		optD=new JRadioButton();
		submit=new JButton("submit and next");
	}
	public void actionPerformed(ActionEvent ae)
	{
		submit.setEnabled(true);
	}
	public void designComponent()
	{
		optA.setText(options[0]);
		optB.setText(options[1]);
		optC.setText(options[2]);
		optD.setText(options[3]);
		optA.setActionCommand(options[0]);
		optB.setActionCommand(options[1]);
		optC.setActionCommand(options[2]);
		optD.setActionCommand(options[3]);
		optA.setFont(new Font("",Font.PLAIN,15));
		optB.setFont(new Font("",Font.PLAIN,15));
		optC.setFont(new Font("",Font.PLAIN,15));
		optD.setFont(new Font("",Font.PLAIN,15));
		questionLabel.setFont(new Font("",Font.ITALIC,15));
		gp.add(optA); gp.add(optB); gp.add(optC); gp.add(optD);
		submit.setFont(new Font("",Font.PLAIN,20));
		submit.setBackground(Color.GRAY);
		submit.setEnabled(false);
		submit.setBackground(Color.white);
		p.add(questionLabel); p.add(optA); p.add(optB); p.add(optC); p.add(optD); p.add(submit);
		add(p);
	}
	public static void main(String...a) throws Exception
	{
		String opts[] = {"kumar","saravana","saravana kumar","kumaran"};
		Date date = new Date();
		int minutes=date.getMinutes();
		int timeScheduled=(minutes+1)%60;
		TestQuestion questions[]=new TestQuestion[10];
		for(int i=0;i<10;i++)
		{
			TestQuestion ques=new TestQuestion("who is me",opts,"sss",1);
			questions[i]=ques;
		}
		while(true)
		{
			Date date1=new Date();
			if(date1.getMinutes()==timeScheduled)
			{
				for(int i=0;i<10;i++)
				    questions[i].dispose();
			}
		}
	}
}