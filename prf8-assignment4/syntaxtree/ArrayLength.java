package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;
import symboltable.Variable;

public class ArrayLength extends Exp {
  public Exp e;
  public Variable var;
  
  public ArrayLength(Exp ae) {
    e=ae;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  public Variable getVar()
  {
    TempCounter temp = new TempCounter();
    if (var == null) var = new Variable("temp" + temp.getCount(), "temp");    return var;  
  }

}
