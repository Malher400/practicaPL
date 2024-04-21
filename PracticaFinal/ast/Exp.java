package ast;

public abstract class Exp implements ASTNode {
    protected Type tipo;
    protected KindExp tipoExp;
    protected int fila;
	protected int columna;

    public NodeKind nodeKind() {return NodeKind.EXPRESSION;}
    
    public Type getTipo() {
    	return tipo;
    }

    public int getFila(){ return fila; }
    public int getColumna() { return columna;}

    public String toString() {return "";}

    public boolean bind(Pila pila){return true;}
}

