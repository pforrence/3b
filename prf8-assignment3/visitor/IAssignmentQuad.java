package visitor;
public class IAssignmentQuad extends Quadruple
{
	public IAssignmentQuad(String op, String arg1, String arg2, String r)
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