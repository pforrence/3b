package visitor;
public class IAssignmentQuad extends Quadruple
{
	public IAssignmentQuad(Object op, Object arg1, Object arg2, Object type)
	{
		operator = op;
		argument1 = arg1;
        argument2 = arg2;
        result = type;
	}
	@Override
    public String toString() {
    	if (result.equals("0"))
        	return operator + " := " + argument1 + "[" + argument2 + "]";    
        else 
        	return operator + "["+argument1+"] := " + argument2;
    }
}