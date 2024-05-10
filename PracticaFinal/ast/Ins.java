package ast;

import errors.TypeException;

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

	public void type() throws TypeException {
	}

	public String generateCode(int depth) {
		return "";
	}

	public int setDelta(int d) {
		return d;
	}
}
