package syntaxtree;
public class TempCounter
{
	private static int counterplusplus;
	private int count;
	
	public TempCounter()
	{
		count = counterplusplus++;
	}
	public void reset()
	{
		counterplusplus = -1;
	}
	public int getCount()
	{
		return count;
	}
}