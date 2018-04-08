package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;
import symboltable.Variable;

public class LessThan extends Exp {
  public Exp e1,e2;
  public Variable var;
  public LessThan(Exp ae1, Exp ae2) {
    e1=ae1; e2=ae2;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  public Variable getVar()
  {
    if (var == null) var = new Variable("temp", "temp");
    return var;
  }
}
