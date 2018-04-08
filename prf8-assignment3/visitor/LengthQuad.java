package visitor;
public class LengthQuad extends Quadruple
{
	public LengthQuad(Object op)
	{
		operator = "length";
		argument1 = op;
	}
	@Override
    public String toString() {
        return operator + " " + argument1;
    }
}