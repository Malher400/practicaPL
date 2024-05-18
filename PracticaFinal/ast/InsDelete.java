package ast;

import errors.TypeException;

public class InsDelete extends Ins {
	private Exp e;

	public InsDelete(int fila, int columna, Exp e) {
		this.fila = fila;
		this.columna = columna;
		this.e = e;
		this.tipoIns = KindIns.DELETE;
	}

	public String toString() {
		return "    |InsDelete| delete " + e.toString() + ";";
	}

	public boolean bind(Pila pila) {
		return e.bind(pila);
	}

	public void type() throws TypeException {
		e.type();
		if (e.getTipo().getKindType() != KindType.POINTER)
			throw new TypeException(fila, columna, "la expresion " + e.toString() + " no es de tipo puntero");
	}
}