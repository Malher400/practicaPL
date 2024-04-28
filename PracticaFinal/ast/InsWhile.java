package ast;

import errors.TypeException;

public class InsWhile extends Ins {
	private Exp e;
	private Ins bloque;

	public InsWhile(int fila, int columna, Exp e, Ins bloque) {
		this.fila = fila;
		this.columna = columna;
		this.bloque = bloque;
		this.e = e;
		this.tipoIns = KindIns.WHILE;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("USSWhile (");
		str.append(e.toString());
		str.append(") ");
		str.append("{\n");
		str.append(bloque.toString());
		str.append("\n}");
		return str.toString();
	}

	public boolean bind(Pila pila) {
		pila.abreBloque();
		boolean b = e.bind(pila) && bloque.bind(pila);
		pila.cierraBloque();

		return b;
	}

	public void type() throws TypeException {
		e.type();
		if (e.getTipo().kindType() == KindType.BOOL) {
			bloque.type();
		} else
			throw new TypeException(e.getFila(), e.getColumna(),
					"La expresion " + e.toString() + " no es de tipo booleano");
	}
}