package ast;

import errors.TypeException;

public abstract class Exp implements ASTNode {
    protected Type tipo;
    protected KindExp tipoExp;
    protected int fila;
    protected int columna;
    protected boolean designador;

    public NodeKind nodeKind() {
        return NodeKind.EXPRESSION;
    }

    public Type getTipo() {
        if (tipo.getKindType() == KindType.REF)
            return tipo.getTipo();
        return tipo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean getDesignador() {
        return designador;
    }

    public Dec getDec(String id) throws TypeException {
        return tipo.getDec(id);
    }

    public String toString() {
        return "";
    }

    public boolean bind(Pila pila) {
        return true;
    }

    public void type() throws TypeException {

    }

}
