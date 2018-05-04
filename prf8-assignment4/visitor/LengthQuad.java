package visitor;
public class LengthQuad extends Quadruple
{
	public LengthQuad(Object op, Object arg1)
	{
		operator = op;
		argument1 = arg1;
	}
	@Override
    public String toString() {
        return operator + " := length " + argument1;
    }
}