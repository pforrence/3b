package visitor;
public class ReturnQuad extends Quadruple
{
	public ReturnQuad(Object op)
	{
		operator = op;
	}
	@Override
    public String toString() {
        return "return " + operator;
    }
}