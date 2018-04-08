package visitor;
public class MethodQuad extends Quadruple
{
	public MethodQuad(String name)
	{
		operator = name;
	}
	@Override
    public String toString() {
        return operator + ": ";
    }
}