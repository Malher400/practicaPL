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
		return "    |InsCall| call " + fun.toString() + ';';
	}

	public boolean bind(Pila pila) {
		boolean b = fun.bind(pila);
		return b;
	}

	public void type() throws TypeException {
		fun.type();
	}

	public String generateCode(int depth) {
		StringBuilder sb = new StringBuilder("");
		sb.append(fun.generateCode(depth));
		if (fun.getTipo().getKindType() != KindType.NULL) {
			sb.append("\tdrop\n"); // Eliminar valor de la pila
		}
		return sb.toString();
	}
}