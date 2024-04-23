package ast;

public abstract class Ins implements ASTNode {
	protected KindIns tipoIns;
	protected int fila;
	protected int columna;

	public NodeKind nodeKind() {
		return NodeKind.INSTRUCTION;
	}

	public KindIns kindIns() {
		return tipoIns;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public boolean bind(Pila pila) {
		return true;
	}
}
