package visitor;
public class MainQuad extends Quadruple
{
	public MainQuad(String name)
	{
		operator = name;
	}
	@Override
    public String toString() {
        return operator + ": ";
    }
}