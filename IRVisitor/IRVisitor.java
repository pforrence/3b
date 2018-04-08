import java.util.ArrayList;

public class IRVisitor implements Visitor {
	
    int temporaryNumber = 0;

    public ArrayList<Quadruple> IR = new ArrayList<Quadruple>();

    public void reset() {
        temporaryNumber = 0;
        IR = new ArrayList<Quadruple>();
    }

	public int genIR(Node n, String operator) {
		Node lhs = n.children.get(0);
		Node rhs = n.children.get(1);

		int l = lhs.accept(this); 
		int r = rhs.accept(this);

		String arg1; 
		String arg2;

		if(lhs instanceof IntNode) {
			arg1 = ""+l;
		}
		else {
			arg1 = "t" + l;
		}

		if(rhs instanceof IntNode) {
			arg2 = ""+r;
		}
		else {
			arg2 = "t" + r;
		}

		IR.add(new Quadruple(operator, arg1, arg2, "t"+temporaryNumber));
		return temporaryNumber++;
	}

	public int visit(AddNode n) {
		return genIR(n, "+");
	}

	public int visit(MinusNode n){
		return genIR(n, "-");
	}

	public int visit(TimesNode n){
		return genIR(n, "*");
	}

	public int visit(DivNode n){
		return genIR(n, "/");
	}

	public int visit(IntNode n) {
		return n.myInt.intValue();
	}
}
