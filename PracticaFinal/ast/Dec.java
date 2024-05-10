package ast;

import errors.TypeException;

public abstract class Dec implements ASTNode {
    protected Type tipo;
    protected String id;
    protected int fila;
    protected int columna;
    protected KindDec tipoDec;
    protected int depth;

    public Dec(int fila, int columna, Type tipo, String id) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.id = id;
    }

    public Type getTipo() {
        return tipo;
    }

    public KindDec getKindDec() {
        return tipoDec;
    }

    public String getId() {
        return id;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public NodeKind nodeKind() {
        return NodeKind.DECLARATION;
    }

    public String toString() {
        return tipo.toString() + " " + id;
    }

    public boolean bind(Pila pila) {
        return true;
    }

    public void type() throws TypeException {
        tipo.type();
    }

    public String generateCode(int depth) {
        return "";
    }

    public int setDelta(int d) {
        return d;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int d) {
        depth = d;
    }
}