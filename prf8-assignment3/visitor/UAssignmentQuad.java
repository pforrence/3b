package visitor;
public class UAssignmentQuad extends Quadruple
{
	public UAssignmentQuad(Object op, Object arg1, Object arg2, Object r)
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