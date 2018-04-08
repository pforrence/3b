package visitor;
public class CondJumpQuad extends Quadruple
{
	public CondJumpQuad(Object op, Object arg1)
	{
		operator = op;
		argument1 = arg1;
	}
	@Override
    public String toString() {
        return "iffalse " + operator + " goto " + argument1;
    }
}