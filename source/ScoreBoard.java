import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.Image;
class ScoreBoard extends JFrame
{
	private int points;
	JPanel p;
	JButton close;
	String correctAnswers[]=new String[10];
	JLabel heading1,heading2,heading3;
	JLabel l[];
	JLabel answers[];
	JLabel givenAnswers[];
	JLabel score;
	JLabel scoreResult;
	int finalScore;
	public ScoreBoard(String[] attempted,String[] corrected,int s)
	{
		finalScore=s;
		for(int i=0;i<10;i++)
		{
			correctAnswers[i]=corrected[i];
		}
		setSize(1280,710);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ScoreBoard");
		buildComponent(attempted);
		designComponent();
		setVisible(true);
	}
	public void buildComponent(String[] attempted)
	{
		close=new JButton("close");
		scoreResult=new JLabel(String.valueOf(finalScore));
		score=new JLabel("Your Score Out Of 10:");
		ImageIcon icon = new ImageIcon("images.jpg");
		Image image=icon.getImage();
		l=new JLabel[10];
		givenAnswers=new JLabel[10];
		heading3=new JLabel("Your Answer");
        heading1=new JLabel("Questions");
		heading2=new JLabel("Actual Answer");
		answers=new JLabel[10];
		int j=-1;
		for(int i=9;i>=0;i--)
	    {
			j++;
			l[j]=new JLabel("question "+(10-i)+":");
			l[j].setForeground(Color.blue);
			answers[j]=new JLabel(correctAnswers[i]);
			answers[j].setFont(new Font("",Font.HANGING_BASELINE,30));
			answers[j].setForeground(Color.blue);
			givenAnswers[j]=new JLabel(attempted[i]);
			givenAnswers[j].setFont(new Font("",Font.HANGING_BASELINE,30));
			givenAnswers[j].setForeground(Color.blue);
		}
		p=new JPanel(new GridLayout(12,3));
		close=new JButton("close");
	}
	public void designComponent()
	{
		heading3.setFont(new Font("",Font.BOLD,35));
		heading1.setFont(new Font("",Font.BOLD,35));
		heading2.setFont(new Font("",Font.BOLD,35));
		heading1.setForeground(Color.pink);
		heading2.setForeground(Color.pink);
		heading3.setForeground(Color.pink);
		p.setBackground(Color.white);
		p.add(heading1);
		p.add(heading2);
		p.add(heading3);
		for(int i=0;i<10;i++)
		{
		   p.add(l[i]);
		   p.add(answers[i]);
		   p.add(givenAnswers[i]);
		   l[i].setFont(new Font("",Font.HANGING_BASELINE,30));
		   l[i].setForeground(Color.BLACK);
		}
		score.setFont(new Font("",Font.BOLD,30));
		scoreResult.setFont(new Font("",Font.BOLD,30));
		close.setBackground(Color.pink);
		close.setFont(new Font("",Font.BOLD,30));
		p.add(score);
		p.add(scoreResult);
		close.setForeground(Color.white);
		p.add(close);
		add(p);
		close.addActionListener((ae)->
		{
			dispose();
		});
	}
}