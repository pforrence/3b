package symboltable;

public class Variable
{
    private String name;
    private String type;
    private int offset; //Used by class member variables for an offset

    
    public Variable(String name, String type)
    {
        this.name = name;
        this.type = type;
        offset = -1;
    }
    
    public Variable(String name, String type, int x)
    {
        this(name, type);
        offset = x;
    }
    
    public void setOffset(int x)
    {
        offset = x;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String toString()
    {
        return getName();
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getOffset()
    {
        return offset;
    }
    
}
