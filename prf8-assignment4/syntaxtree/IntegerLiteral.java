package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;
import symboltable.Variable;

public class IntegerLiteral extends Exp {
  public int i;
  public Variable var;
  public IntegerLiteral(int ai) {
    i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  public Variable getVar()
  {
    if (var == null) return new Variable(Integer.toString(i), "int");
    return var;
  }
}
