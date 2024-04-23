package ast;

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
		StringBuilder str = new StringBuilder("trotskIf (");
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
}