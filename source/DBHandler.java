import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class DBHandler
{
	Statement st;
	Connection con;
	public DBHandler()
	{
	   try
	   {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("no probs");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hostel","root","muthusiva@0314");
		System.out.println("Successfully connected");
		st=con.createStatement();
	   }
	   catch(Exception fne)
	   {
		  System.out.println(fne);
	   }
		
	}
	public int insertCandidate(Candidate c)
	{
	 int x=0;
	 try
	 {
	     if(!isUnique(c))
	      return x;
	 
         else
	     {
          try
	      {
		   String statement = "insert into candidate values('"+c.getName()+"',"+c.getRank()+",'"+c.getQualification()+"','"+c.getEmail()+"','"+c.getPassword()+"')";
		   x=st.executeUpdate(statement);
		   String empty="";
		   String statement2 = "insert into Answer values('"+c.getEmail()+"','"+empty+"','"+empty+"','"+empty+"','"+empty+"','"+empty+"','"+empty+"','"+empty+"','"+empty+"','"+empty+"','"+empty+"')";
		   st.executeUpdate(statement2);
		  }
	     catch(Exception e)
	     {
		 System.out.println(e);
	     }
	    }
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return x;
	}
	public String retrieveHighScorer(String sub)
	{
	 String statement="select * from Score"+sub;
	 try
	 {
		ResultSet result=st.executeQuery(statement);
		System.out.println("yes in");
		String emailId=null;
		Integer max=0;
		boolean var=result.next();
		if(var)
		{
		 result.last();
		 int rowCount=result.getRow();	
         result.first();
		 System.out.println("here");
         for(int i=0;i<rowCount;i++)
		 {
			 if(max<Integer.parseInt(result.getString("Score")))
			   max=Integer.parseInt(result.getString("Score"));
			 result.next();
		 }
		 String statement2="select * from Score"+sub+" where Score="+max+"";
		 try
		 {
				ResultSet result2=st.executeQuery(statement2);
				result2.next();
				System.out.println("end");
				emailId=result2.getString("EmailId");
		 }
		 catch(Exception e)
		 {
				e.printStackTrace();
		 }
			return emailId;
		}
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
	 return null;
	}
	public Candidate retrieveCandidate(String email)
	{
		System.out.println(email);
		String statement="select * from Candidate where EmailId='"+email+"'";
		ResultSet result;
		try
		{
		   result=st.executeQuery(statement);
		   boolean var=result.next();
		   if(var)
		   {
			  System.out.println("var is true");
			  String name=result.getString("Name");
			  System.out.println(result.getString("Qualification"));
			  String qualification=result.getString("Qualification");
			  Candidate candidate=new Candidate(name,qualification,email,"");
			  return candidate;
		   }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("not true");
		return null;
	}
	public boolean isScoreUnique(String email,String sub)
	{
		String statement="select * from Score"+sub+" where EmailId='"+email+"'";
		ResultSet result=null;
		boolean var=false;
		try
		{
			result=st.executeQuery(statement);
			var=!result.next();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return var;
	}
	public boolean insertScore(String email,int score,String subject)
	{
		int x=0;
		if(isScoreUnique(email,subject))
		{
		  try
		  {		  
			String statement="insert into Score"+subject+" values('"+email+"',"+score+")";
			x=st.executeUpdate(statement);
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		}
		else
		{
		   try
		   {
			String statement="update Score"+subject+" set Score='"+score+"' where EmailId='"+email+"'";
			x=st.executeUpdate(statement);
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		}
		if(x>0)
		  return true;
		return false;
	}
	public boolean setRank(Candidate candidate,int rank)
	{
		int x=0;
		String statement="update Candidate set Rank= "+rank+" where EmailId='"+candidate.getEmail()+"'";
		try
		{
		  x=st.executeUpdate(statement);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(x>0)
		  return true;
		return false;
	}
	public boolean searchCandidate(Candidate c) throws Exception
	{
		ResultSet s;
		String email=c.getEmail();
		String pass=c.getPassword();
		String name=c.getName();
	    String statement =  "select * from candidate where EmailId='"+email+"' and Password='"+pass+"' and name='"+name+"'";
	    s=st.executeQuery(statement);
		boolean boolVar=s.next();
		if(boolVar==true)
		{
		  for(int i=0;i<10;i++)
		  {
			String ans="ans"+(i+1);
			String nullValue="NULL";
			String statement2="update Answer set "+ans+" ='"+nullValue+"' where EmailId='"+email+"'";
			st.executeUpdate(statement2);
		  }
		}
		return boolVar;
	}
	public boolean isUnique(Candidate c)throws Exception 
	{
		String email=c.getEmail();
		String statement ="select * from candidate where EmailId='"+email+"'";
		ResultSet s=st.executeQuery(statement);
		return !s.next();
	}
	public String getAns(String email,int questionNo)
	{
		String ans="ans"+questionNo;
		String statement="select * from Answer where EmailId='"+email+"'";
		String obtained="";
		try
		{
		  ResultSet result=st.executeQuery(statement);
		  System.out.println("reached");
		  result.next();
		  obtained=result.getString(ans);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return obtained;
	}
	public int setAnswer(String email,int questionNo,String answer)
	{
		int x=0;
		System.out.println("setAnswer: "+questionNo);
		String ans="ans"+questionNo;
		String statement="update Answer set "+ans+" = '"+answer+"' where EmailId='"+email+"'";
		try
		{
	      x=st.executeUpdate(statement);
		  if(x>0)
		     System.out.println("successful");
		  else if(x<0)
		      System.out.println("unsuccessful");
		  else
		      System.out.println("data not found");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return x;
	}
}
 class DataBaseTest
{
	public static void main(String[] args) throws Exception
	{
		DBHandler db = new DBHandler();
		//Candidate candidate=db.retrieveCandidate("ss");
		System.out.println("here not");
		System.out.println(db.insertScore("ssss",10,"Maths"));
	}
}