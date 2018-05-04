package visitor;
public class CopyQuad extends Quadruple
{
	public CopyQuad(Object op, Object arg1)
	{
		operator = op;
		argument1 = arg1;
	}
	@Override
    public String toString() {
        return operator + " := " + argument1;
    }
}