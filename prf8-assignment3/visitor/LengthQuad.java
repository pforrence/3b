package visitor;
public class LengthQuad extends Quadruple
{
	public LengthQuad(String op)
	{
		operator = "length";
		argument1 = op;
	}
	@Override
    public String toString() {
        return operator + " " + argument1;
    }
}