package visitor;
public class LabelQuad extends Quadruple
{
	public LabelQuad(Object name)
	{
		operator = name;
	}
	@Override
    public String toString() {
        return operator + ": ";
    }
}