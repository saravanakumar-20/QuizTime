import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
class Timer extends JFrame
{
	JLabel minutes;
	JLabel seconds;
	JLabel collan;
	JPanel p;
	public Timer()
	{
		setVisible(true);
		buildComponent();
		designComponent(0);
	}
	public void buildComponent()
	{
		minutes=new JLabel("");
		seconds=new JLabel("");
		collan=new JLabel(":");
		minutes.setFont(new Font("",Font.BOLD,40));
		seconds.setFont(new Font("",Font.BOLD,40));
		collan.setFont(new Font("",Font.BOLD,40));
	}
	public void designComponent(int count)
	{
		if(count==1500)
		   dispose();
		else
		{
			p=new JPanel(new GridLayout(1,3));
			if(count>60)
			{
			  String sec=""+(count-60);
			  String min=""+(int)(count/60);
			  minutes=new JLabel(min);
			  seconds=new JLabel(sec);
			  p.add(minutes);
			  p.add(collan);
			  p.add(seconds);
			  add(p);
			  Thread.sleep(1000);
			  designComponent(count+1);
			}
			else
			{
				minutes=new JLabel("0");
				seconds=new JLabel(String.valueOf(count));
				p.add(minutes);
				p.add(collan);
				p.add(seconds);
				add(p);
				Thread.sleep(1000);
				designComponent(count+1);
			}
			
		}
	}
}
public class TimerTest 
{
	public static void main(String...args)
	{
		Timer timer=new Timer();
	}
}