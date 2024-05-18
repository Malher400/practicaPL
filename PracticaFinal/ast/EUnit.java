package ast;

import errors.TypeException;

public abstract class EUnit extends Exp {
	protected Exp opnd;

	public EUnit(int fila, int columna, KindExp expTipo, Exp op) {
		this.fila = fila;
		this.columna = columna;
		this.tipoExp = expTipo;
		this.opnd = op;
	}

	public Exp getOpnd() {
		return opnd;
	}

	public String toString() {
		return "  |EUnit| " + tipoExp.toString() + '(' + opnd.toString() + ')';
	}

	public boolean bind(Pila pila) {
		return opnd.bind(pila);
	}

	public void type() throws TypeException {
		opnd.type();
		designador = false;
	}

	public String generateCode(int depth) {
		StringBuilder ss = new StringBuilder();
		ss.append(opnd.generateCode(depth));
		return ss.toString();
	}
}
