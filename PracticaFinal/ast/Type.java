package ast;

import errors.TypeException;

public class Type implements ASTNode {
    protected KindType tipoType;
    protected int fila;
    protected int columna;

    public NodeKind nodeKind() {
        return NodeKind.TYPE;
    }

    public KindType getKindType() {
        return tipoType;
    }

    public int getFila() {
        return fila;
    }

    public Type getTipo() {
        return null;
    }

    public int getColumna() {
        return columna;
    }

    public Dec getDec(String id) throws TypeException {
        throw new TypeException(fila, columna, "El tipo " + this.toString() + " no tiene declaracion");
    }

    public boolean isAssignable() {
        return false;
    }

    public boolean isWritable() {
        return false;
    }

    public String getId() {
        return tipoType.toString();
    }

    public boolean bind(Pila pila) {
        return true;
    }

    public void type() throws TypeException {

    }

}
