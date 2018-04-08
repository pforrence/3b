package syntaxtree;

import visitor.Visitor;
import visitor.TypeVisitor;

public class VoidType extends Type 
{
  public void accept(Visitor v) 
  {}

  public Type accept(TypeVisitor v) 
  {
    return null;
  }
}
