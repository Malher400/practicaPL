package ast;

public interface ASTNode {
    public void type();

    // public ?? generateCode() // for the future
    public boolean bind(Pila pila);

    public NodeKind nodeKind();

    public String toString();
}
