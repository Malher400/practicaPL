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
		if (e.getTipo().getKindType() == KindType.BOOL) {
			bloque.type();
		} else
			throw new TypeException(e.getFila(), e.getColumna(),
					"La expresion " + e.toString() + " no es de tipo booleano");
	}

	public int setDelta(int d) {
		return bloque.setDelta(d);
	}

	public String generateCode(int depth) {
		StringBuilder ss = new StringBuilder();
		ss.append("block\n");
		ss.append("loop\n");
		ss.append(e.generateCode(depth));
		ss.append("i32.eqz\n");
		ss.append("br_if 1\n");
		sb.append(bloque.generateCode(depth));
		ss.append("br 0\n");
		ss.append("end\n");
		ss.append("end\n");
		return ss.toString();
	}

}