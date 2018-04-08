package visitor;
public class ReturnQuad extends Quadruple
{
	public ReturnQuad(Object op, Object arg1, Object arg2, Object r)
	{
		operator = op;
		argument1 = arg1;
        argument2 = arg2;
        result = r;
	}
	@Override
    public String toString() {
        return result + " := " + argument1 + " " + operator + 
                  " " + argument2;
    }
}