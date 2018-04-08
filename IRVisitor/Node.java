import java.util.*;

abstract class Node { 
	Node parent; 
	ArrayList<Node> children;
	
	Node() {
		children = new ArrayList<Node>();
	}

	public abstract int visit() ;
	public abstract int accept(Visitor v);
}


class IntNode extends Node { 
	Integer myInt;
    
	public IntNode(Integer i) { 
		myInt = i; 
	}

    public int visit() {
        System.out.print(myInt);
		return myInt.intValue();
    }

	public int accept(Visitor v) {
		return v.visit(this);
	}
}

class AddNode extends Node { 
	public AddNode(Node l, Node r) {
        children.add(l); 
		children.add(r);
    }

    public int visit() {
        int l = children.get(0).visit();
        System.out.print(" + ");
        int r = children.get(1).visit();

		return l + r;
    }

	public int accept(Visitor v) {
		return v.visit(this);
	}
}

class MinusNode extends Node { 
	public MinusNode(Node l, Node r) {
        children.add(l); 
		children.add(r);
    }

    public int visit() {
        int l = children.get(0).visit();
        System.out.print(" - ");
        int r = children.get(1).visit();

		return l - r;
    }

	public int accept(Visitor v) {
		return v.visit(this);
	}
}

class TimesNode extends Node {
	public TimesNode(Node l, Node r) {
        children.add(l); 
		children.add(r);
    }

    public int visit() {
        Node leftChild = children.get(0);
		Node rightChild = children.get(1);
		int l = 0;
		int r = 0;

		if(leftChild instanceof AddNode || leftChild instanceof MinusNode) {
			System.out.print("( ");
			l = leftChild.visit();
			System.out.print(" )");
		}
        else {
			l = leftChild.visit();
		}

		System.out.print(" * ");
        
		if(rightChild instanceof AddNode || rightChild instanceof MinusNode) {
			System.out.print("( ");
			r = rightChild.visit();
			System.out.print(" )");
		}
        else {
			r = rightChild.visit();
		}

		return l * r;
    }

	public int accept(Visitor v) {
		return v.visit(this);
	}
}

class DivNode extends Node {
	public DivNode(Node l, Node r) {
        children.add(l); 
		children.add(r);
    }

    public int visit() {
        int l = children.get(0).visit();
        System.out.print(" / ");
        int r = children.get(1).visit();

		return l / r;
    }

	public int accept(Visitor v) {
		return v.visit(this);
	}
}


