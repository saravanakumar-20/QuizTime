import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;
class MyTimer extends JFrame
{
	JLabel minutes;
	JLabel seconds;
	JLabel collan;
	JPanel p;
	public MyTimer(String min,String sec)
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - 300;
        int y = 0;
        setLocation(x, y);
		setVisible(true);
		setSize(300,150);
		p=new JPanel(new GridLayout(1,3))
		{
			public void paintComponent(Graphics g)
			{
				ImageIcon icon=new ImageIcon("book6.jpg");
				Image image=icon.getImage();
				g.drawImage(image,0,0,null);
			}
		};
		minutes=new JLabel(min);
		seconds=new JLabel(sec);
		collan=new JLabel(":");
		minutes.setFont(new Font("",Font.BOLD,60));
		seconds.setFont(new Font("",Font.BOLD,60));
		collan.setFont(new Font("",Font.BOLD,60));
		p.add(minutes);
		p.add(collan);
		p.add(seconds);
		add(p);
	}
}
public class TimerTest 
{
	 MyTimer timer;
	 boolean disperse=false;
	 public void schedule(int totalSeconds)
	 {
		String min="";
		String sec="";
		int count=0;
		 while(count!=(totalSeconds+1))
		 {
			System.out.println(disperse);
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
	 }
	public void dispose()
	{
		disperse=true;
	}
	public static void main(String...args)
	{
	    TimerTest time=new TimerTest();
		time.schedule(10);
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println(e.getClass().getName());
		}	
	}
}