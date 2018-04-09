package visitor;
public class UAssignmentQuad extends Quadruple
{
	public UAssignmentQuad(Object op, Object arg1, Object r)
	{
		operator = op;
		argument1 = arg1;
        result = r;
	}
	@Override
    public String toString() {
        return result + " := " + operator + argument1;
    }
}