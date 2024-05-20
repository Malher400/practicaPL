package ast;

import errors.TypeException;

public class InsIf extends Ins {
	private Exp e;
	private Ins bloque;

	public InsIf(int fila, int columna, Exp e, Ins bloque) {
		this.fila = fila;
		this.columna = columna;
		this.tipoIns = KindIns.IF;
		this.bloque = bloque;
		this.e = e;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("    |InsIf| trotskIf (");
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

	public void setDepth(int d){
		bloque.setDepth(d);
	}

	public String generateCode(int depth) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.generateCode(depth));
		sb.append("\tif\n");
		sb.append(bloque.generateCode(depth));
		sb.append("\tend\n");
		return sb.toString();
	}
}