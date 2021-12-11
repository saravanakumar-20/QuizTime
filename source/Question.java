public class Question
{
	private String prompt;
	private String ans;
	public Question(String prompt,String ans)
	{
		this.prompt=prompt;
		this.ans=ans;
	}
	public void setPrompt(String prompt)
	{
		this.prompt=prompt;
	}
	public void setAnswer(Stirng ans)
	{
		this.ans=ans;
	}
	public String getPrompt()
	{
		return prompt;
	}
	public String getAnswer()
	{
		return ans;
	}
}