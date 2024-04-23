package ast;

public interface ASTNode {
    // public void type(); // for the future
    // public ?? generateCode() // for the future
    public boolean bind(Pila pila);

    public NodeKind nodeKind();

    public String toString();
}
