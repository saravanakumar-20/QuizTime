import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Candidate 
{
	private String name;
	private String email;
	private String qualification;
	private String password;
    private long rank;
	public Candidate(String email)
	{
		this.email=email;
		name="";
		qualification="";
		rank=1;
		password="";
	}
	public Candidate()
	{
		name="Candidate";
		rank=1;
	}
	public Candidate(String name,String email,String pass)
	{
		this.name=name;
		this.email=email;
		this.password=pass;
		rank=1;
		qualification="";
	}
	public Candidate(String name,String qualification,String email,String password)
	{
		this.name=name;
		this.qualification=qualification;
		this.email=email;
		this.rank=1;
		this.password=password;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return name;
	}
	public void setRank(int rank)
	{
		this.rank=rank;
	}
	public long getRank()
	{
		return rank;
	}
	public String getEmail()
	{
		return email;
	}
	public void setQualification(String qualification)
	{
		this.qualification=qualification;
	}
	public String getQualification()
	{
		return qualification;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public String getPassword()
	{
		return password;
	}
	public void displayCandidate()
	{
		System.out.println("Name: "+name);
		System.out.println("Rank: "+rank);
	}
	
}
/*class DBHandler
{
	Statement st;
	Connection con;
	public DBHandler()
	{
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/attender","root","");
		st=con.createStatement();
		
	}
}*/