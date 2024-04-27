package ast;

import errors.TypeException;

public interface ASTNode {
    public void type() throws TypeException; // for the future

    // public ?? generateCode() // for the future

    public boolean bind(Pila pila);

    public NodeKind nodeKind();

    public String toString();
}
