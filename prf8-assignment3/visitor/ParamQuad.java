package visitor;

public class ParamQuad extends Quadruple
{
	public ParamQuad(Object op)
	{
		operator = "param";
		argument1 = op;
	}
	@Override
    public String toString() {
        return operator + " " + argument1;
    }
}