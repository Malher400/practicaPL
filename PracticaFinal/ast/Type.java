package ast;

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

    public int getColumna() {
        return columna;
    }

    public String getId() {
        return tipoType.toString();
    }

    public boolean bind(Pila pila) {
        return true;
    }

}
