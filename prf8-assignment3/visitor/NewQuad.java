package visitor;
public class NewQuad extends Quadruple
{
	public NewQuad(Object type, Object id)
	{
		operator = id;
		argument1 = type;
	}
	@Override
    public String toString() {
        return operator + " := new " + argument1;
    }
}