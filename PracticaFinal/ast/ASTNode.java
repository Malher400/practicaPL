package ast;

import errors.TypeException;

public interface ASTNode {
    public void type() throws TypeException; // for the future

    public String generateCode(int depth);

    public int setDelta(int d);

    public void setDepth(int d);

    public boolean bind(Pila pila);

    public NodeKind nodeKind();

    public String toString();
}
