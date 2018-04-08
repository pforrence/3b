public class Interpreter implements Visitor {
	
	public int visit(AddNode n) {
		Node lhs = n.children.get(0);
		Node rhs = n.children.get(1);
		int l; int r;
		l = lhs.accept(this);
		System.out.print( " + " );
		r = rhs.accept(this);
		return l+r;
	}

	public int visit(MinusNode n) {
		Node lhs = n.children.get(0);
		Node rhs = n.children.get(1);
		int l; int r;
		l = lhs.accept(this);
		System.out.print( " - " );
		r = rhs.accept(this);
		return l-r;
	}

	public int visit(TimesNode n) {
		Node lhs = n.children.get(0);
		Node rhs = n.children.get(1);
		int l; int r;

		if(lhs instanceof AddNode || lhs instanceof MinusNode) {
			System.out.print("( ");
			l = lhs.accept(this);
			System.out.print(" )");
		}
		else {
			l = lhs.accept(this);
		}

		System.out.print(" * ");

		if(rhs instanceof AddNode || rhs instanceof MinusNode) {
			System.out.print("( ");
			r = lhs.accept(this);
			System.out.print(" )");
		}
		else {
			r = rhs.accept(this);
		}

		return l*r;
	}

	public int visit(DivNode n) {
		Node lhs = n.children.get(0);
		Node rhs = n.children.get(1);
		int l; int r;

		if(lhs instanceof AddNode || lhs instanceof MinusNode) {
			System.out.print("( ");
			l = lhs.accept(this);
			System.out.print(" )");
		}
		else {
			l = lhs.accept(this);
		}

		System.out.print(" / ");

		if(rhs instanceof AddNode || rhs instanceof MinusNode) {
			System.out.print("( ");
			r = lhs.accept(this);
			System.out.print(" )");
		}
		else {
			r = rhs.accept(this);
		}

		return l/r;
	}

	public int visit(IntNode n) {
		System.out.print(n.myInt.intValue());
		return n.myInt.intValue();
	}
}
