public interface Visitor {
	public int visit(AddNode n);
	public int visit(MinusNode n);
	public int visit(TimesNode n);
	public int visit(DivNode n);
	public int visit(IntNode n);
}
