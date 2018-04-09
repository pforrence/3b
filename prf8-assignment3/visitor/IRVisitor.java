package visitor;

import syntaxtree.*;
import symboltable.*;
import java.util.ArrayList;

public class IRVisitor implements Visitor {
  //public int tempcounter = 0;
  public Scope symtable;
  public int blockcounter = 0;
  //public int labelcounter = 0;
  //public int tempcounter = 0;
  public int whilecounter = 0;
  public int ifcounter = 0;
  public ArrayList<Quadruple> IR = new ArrayList<Quadruple>();
  
  public IRVisitor(Scope st)
  {
    symtable = st;
  }
  public void reset() {
      IR = new ArrayList<Quadruple>();
      blockcounter = 0;
      //tempcounter = 0;
      whilecounter = 0;
      ifcounter = 0;
  }
  // MainClass m;
  // ClassDeclList cl;
  public void visit(Program n) {
    n.m.accept(this);
    for ( int i = 0; i < n.cl.size(); i++ ) {
        System.out.println();
        n.cl.elementAt(i).accept(this);
    }
  }
  
  // Identifier i1,i2;
  // Statement s;
  public void visit(MainClass n) {
    //IR.add(new MainQuad(n.i1.toString()));
    symtable = symtable.enterScope(n.i1.toString());
    symtable = symtable.enterScope("main");

    //System.out.print("class ");
    n.i1.accept(this); //classname
    //System.out.println(" {");
    IR.add(new LabelQuad((Object)"main"));
    //System.out.print("  public static void main (String [] ");
    //System.out.print("param ");
    n.i2.accept(this); //argument list name (String [] il2)

    // System.out.println(") {");
    // System.out.print("    ");
    n.s.accept(this); //statment list.
    //System.out.println("  }");
    // System.out.println("}");
    //IR.add(new MainQuad(n.i1.toString()));
    symtable = symtable.exitScope();
    symtable = symtable.exitScope();

  }

  // Identifier i;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclSimple n) {
    //IR.add(new MainQuad(n.i1.toString()));
    symtable = symtable.enterScope(n.i.toString());

    //System.out.print("class ");
    n.i.accept(this);
    //System.out.println(" { ");
    for ( int i = 0; i < n.vl.size(); i++ ) {
        //System.out.print("  ");
        n.vl.elementAt(i).accept(this);
        if ( i+1 < n.vl.size() ) { /*System.out.println();*/ }
    }
    for ( int i = 0; i < n.ml.size(); i++ ) {
        //System.out.println();
        n.ml.elementAt(i).accept(this);
    }
    //System.out.println();
    //System.out.println("}");
    symtable = symtable.exitScope();

  }
 
  // Identifier i;
  // Identifier j;
  // VarDeclList vl;
  // MethodDeclList ml;
  public void visit(ClassDeclExtends n) {
    symtable = symtable.enterScope(n.i.toString());

    // System.out.print("class ");
    n.i.accept(this);
    // System.out.println(" extends ");
    n.j.accept(this);
    // System.out.println(" { ");
    for ( int i = 0; i < n.vl.size(); i++ ) {
        // System.out.print("  ");
        n.vl.elementAt(i).accept(this);
        if ( i+1 < n.vl.size() ) { /*System.out.println();*/ }
    }
    for ( int i = 0; i < n.ml.size(); i++ ) {
        // System.out.println();
        n.ml.elementAt(i).accept(this);
    }
    // System.out.println();
    //System.out.println("}");
    symtable = symtable.exitScope();

  }

  // Type t;
  // Identifier i;
  public void visit(VarDecl n) {
    IR.add(new NewQuad(n.t.toString(), n.i.toString()));
    n.t.accept(this);
    //System.out.print(" ");
    n.i.accept(this);
    //System.out.print(";");
  }

  // Type t;
  // Identifier i;
  // FormalList fl;
  // VarDeclList vl;
  // StatementList sl;
  // Exp e;
  public void visit(MethodDecl n) {
    IR.add(new LabelQuad(n.i.toString()));
    symtable = symtable.enterScope(n.i.toString());

    //System.out.print("  public ");
    n.t.accept(this);
    //System.out.print(" ");
    n.i.accept(this);
    //System.out.print(" (");
    for ( int i = 0; i < n.fl.size(); i++ ) {
        n.fl.elementAt(i).accept(this);
        if (i+1 < n.fl.size()) { 
          //System.out.print(", "); 
        }
    }
    //System.out.println(") { ");
    for ( int i = 0; i < n.vl.size(); i++ ) {
        //System.out.print("    ");
        n.vl.elementAt(i).accept(this);
        //System.out.println("");
    }
    for ( int i = 0; i < n.sl.size(); i++ ) {
        //System.out.print("    ");
        n.sl.elementAt(i).accept(this);
        if ( i < n.sl.size() ) { 
          //System.out.println(""); 
        }
    }
    //System.out.print("    return ");

    n.e.accept(this);
    IR.add(new ReturnQuad(n.e.getVar()));

    // System.out.println(";");
    // System.out.print("  }");
    symtable = symtable.exitScope();
  }

  // Type t;
  // Identifier i;
  public void visit(Formal n) {
    n.t.accept(this);
    //System.out.print(" ");
    n.i.accept(this);
  }

  public void visit(IntArrayType n) {
    //System.out.print("int []");
  }

  public void visit(BooleanType n) {
    //System.out.print("boolean");
  }

  public void visit(IntegerType n) {
    //System.out.print("int");
  }

  // String s;
  public void visit(IdentifierType n) {
    //System.out.print(n.s);
  }

  // StatementList sl;
  public void visit(Block n) {
    //System.out.println("{ ");
    blockcounter++;
    symtable = symtable.enterScope(Integer.toString(blockcounter));
    for ( int i = 0; i < n.sl.size(); i++ ) {
        //System.out.print("      ");
        n.sl.elementAt(i).accept(this);
        //System.out.println();
    }
    //System.out.print("    } ");
    symtable = symtable.exitScope();
  }

  // Exp e;
  // Statement s1,s2;
  public void visit(If n) {
    int local = ifcounter++;
    String if_label = "ifbreak" + local;
    String else_label = "else" + local;

    //System.out.print("if (");
    n.e.accept(this);
    IR.add(new CondJumpQuad(n.e.getVar(), else_label));

    //System.out.println(") ");
    //System.out.print("    ");
    n.s1.accept(this);
    IR.add(new UCondJumpQuad((Object)(if_label)));
    IR.add(new LabelQuad((Object)(else_label)));

    //System.out.println();
    //System.out.print("    else ");
    n.s2.accept(this);
    IR.add(new LabelQuad((Object)(if_label)));

  }

  // Exp e;
  // Statement s;
  public void visit(While n) {
    String while_label = "while" + whilecounter++;
    String break_while_label = "break" + while_label;


    IR.add(new LabelQuad((Object)(while_label)));
    //System.out.print("while (");
    n.e.accept(this);
    IR.add(new CondJumpQuad(n.e.getVar(), break_while_label));

    //System.out.print(") ");
    n.s.accept(this);
    IR.add(new UCondJumpQuad((Object)(while_label)));
    IR.add(new LabelQuad((Object)(break_while_label)));

  }

  // Exp e;
  public void visit(Print n) {
    //System.out.print("System.out.println(");
    n.e.accept(this);
    //System.out.print(");");
    IR.add(new ParamQuad(n.e.getVar()));
    IR.add(new CallQuad("print", "1"));
  }
  
  // Identifier i;
  // Exp e;
  public void visit(Assign n) {
    n.i.accept(this);
    //System.out.print(" = ");
    n.e.accept(this);
    //System.out.print(";");
    IR.add(new CopyQuad(n.i.toString(), n.e.getVar()));
  }

  // Identifier i;
  // Exp e1,e2;
  public void visit(ArrayAssign n) {
    n.i.accept(this);
    //System.out.print("[");
    n.e1.accept(this);
    //System.out.print("] = ");
    n.e2.accept(this);
    //System.out.print(";");
    IR.add(new IAssignmentQuad(n.i.toString(), n.e1.getVar(), n.e2.getVar(), "1"));

  }

  // Exp e1,e2;
  public void visit(And n) {
    //System.out.print("(");
    n.e1.accept(this);
    //System.out.print(" && ");
    n.e2.accept(this);
    //System.out.print(")");
    IR.add(new AssignmentQuad("&&", n.e1.getVar(), n.e2.getVar(), n.getVar()));

  }

  // Exp e1,e2;
  public void visit(LessThan n) {
    //System.out.print("(");
    n.e1.accept(this);
    //System.out.print(" < ");
    n.e2.accept(this);
    //System.out.print(")");
    IR.add(new AssignmentQuad("<", n.e1.getVar(), n.e2.getVar(), n.getVar()));
  }

  // Exp e1,e2;
  public void visit(Plus n) {
    //System.out.print("(");
    n.e1.accept(this);
    //System.out.print(" + ");
    n.e2.accept(this);
    //System.out.print(")");
    IR.add(new AssignmentQuad("+", n.e1.getVar(), n.e2.getVar(), n.getVar()));

  }

  // Exp e1,e2;
  public void visit(Minus n) {
    //System.out.print("(");
    n.e1.accept(this);
    //System.out.print(" - ");
    n.e2.accept(this);
    //System.out.print(")");
    IR.add(new AssignmentQuad("-", n.e1.getVar(), n.e2.getVar(), n.getVar()));
  }

  // Exp e1,e2;
  public void visit(Times n) {
    //System.out.print("(");
    n.e1.accept(this);
    //System.out.print(" * ");
    n.e2.accept(this);
    //System.out.print(")");
    IR.add(new AssignmentQuad("*", n.e1.getVar(), n.e2.getVar(), n.getVar()));
  }

  // Exp e1,e2;
  public void visit(ArrayLookup n) {
    n.e1.accept(this);
    //System.out.print("[");
    n.e2.accept(this);
    //System.out.print("]");
    IR.add(new IAssignmentQuad(n.getVar(), n.e1.getVar(), n.e2.getVar(), "0"));

  }

  // Exp e;
  public void visit(ArrayLength n) {
    IR.add(new LengthQuad(n.getVar(), n.e.getVar()));
    n.e.accept(this);
    //System.out.print(".length");
  }

  // Exp e;
  // Identifier i;
  // ExpList el;
  public void visit(Call n) {
    n.e.accept(this);
    //System.out.print(".");
    n.i.accept(this);
    //System.out.print("(");
    int i;
    for (i = 0; i < n.el.size(); i++ ) {
        n.el.elementAt(i).accept(this);
        IR.add(new ParamQuad(n.el.elementAt(i).getVar()));
        if ( i+1 < n.el.size() ) { //System.out.print(", "); 
      }
    }
    IR.add(new ParamQuad(n.e.getVar()));
    i++;
    //System.out.print(")");
    IR.add(new CallQuad(n.i.toString(), Integer.toString(i)));
  }

  // int i;
  public void visit(IntegerLiteral n) {
    //System.out.print(n.i);
  }

  public void visit(True n) {
    //System.out.print("true");
  }

  public void visit(False n) {
    //System.out.print("false");
  }

  // String s;
  public void visit(IdentifierExp n) {
    //System.out.print(n.s);
  }

  public void visit(This n) {
    //System.out.print("this");
  }

  // Exp e;
  public void visit(NewArray n) {
    //System.out.print("new int [");
    n.e.accept(this);
    //System.out.print("]");
    IR.add(new NewArrayQuad("int", n.getVar(), n.e.getVar()));

  }

  // Identifier i;
  public void visit(NewObject n) {
    //System.out.print("new ");
    //System.out.print(n.i.s);
    //System.out.print("()");
    IR.add(new NewQuad(n.i.toString(), n.getVar()));
  }

  // Exp e;
  public void visit(Not n) {
    //System.out.print("!");
    n.e.accept(this);
    IR.add(new UAssignmentQuad("!", n.e.getVar(), n.getVar()));

  }

  // String s;
  public void visit(Identifier n) {
    //System.out.print(n.s);
  }
}
