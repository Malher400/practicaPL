package ast;

import errors.TypeException;

public class Type implements ASTNode {
    protected KindType tipoType;
    protected int fila;
    protected int columna;
    protected int size;

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
        throw new TypeException(fila, columna, "El tipo " + this.toString() + " no tiene declaracion.");
    }

    public Dec getDec(int id) throws TypeException {
        throw new TypeException(fila, columna, "El tipo " + this.toString() + " no tiene declaracion.");
    }

    public Dec getDec() {
        return null;
    }

    public boolean equals(Type otro) throws TypeException {
        if (otro == null)
            return false;
        return tipoType == otro.getKindType();
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

    public int getSize() {
        return size;
    }

    public void setSize() {
        size = 4;
    }

    public boolean bind(Pila pila) {
        return true;
    }

    public void type() throws TypeException { // Esto luego servira para ir reservando el espacio que ocupa cada cosa
        setSize();
    }

    public String generateCode(int depth) {
        return "";
    }

    public int setDelta(int d) {
        return d;
    }
    public void setDepth(int d) {   }

}
