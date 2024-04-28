package ast;

import errors.TypeException;

public class InsCall extends Ins {
	private Exp fun;

	public InsCall(int fila, int columna, Exp fun) {
		this.fila = fila;
		this.columna = columna;
		this.fun = fun;
		this.tipoIns = KindIns.CALL;
	}

	public String toString() {
		return "call " + fun.toString() + ';';
	}

	public boolean bind(Pila pila) {
		return fun.bind(pila);
	}

	public void type() throws TypeException {
		fun.type();
	}
}