package visitor;
public class NewArrayQuad extends Quadruple
{
	public NewArrayQuad(Object type, Object id, Object size)
	{
		operator = id;
		argument1 = type;
		argument2 = size;
	}
	@Override
    public String toString() {
        return operator + " := new " + argument1 + ", " + argument2;
    }
}