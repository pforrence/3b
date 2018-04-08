package visitor;
public class CallQuad extends Quadruple
{
	public CallQuad(Object name, Object arg1)
	{
		operator = name;
		argument1 = arg1;
	}
	@Override
    public String toString() {
        return "call " + operator + ", " + argument1;
    }
}