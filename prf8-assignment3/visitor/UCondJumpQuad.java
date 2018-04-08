package visitor;
public class UCondJumpQuad extends Quadruple
{
	public UCondJumpQuad(Object op)
	{
		operator = op;
	}
	@Override
    public String toString() {
        return "goto " + operator;
    }
}