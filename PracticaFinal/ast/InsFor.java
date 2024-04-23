package ast;

public class InsFor extends Ins {
	private Exp e;
	private Ins asig;
	private Ins bloque;
	private Dec dec;

	public InsFor(int fila, int columna, Dec dec, Exp e, Ins asig, Ins bloque) {
		this.fila = fila;
		this.columna = columna;
		this.dec = dec;
		this.e = e;
		this.asig = asig;
		this.bloque = bloque;
		this.tipoIns = KindIns.FOR;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("forLetariat ( ");
		str.append(dec.toString());
		str.append(" ");
		str.append(e.toString());
		str.append("; ");
		str.append(asig.toString());
		str.append(" ) ");
		str.append("{\n");
		str.append(bloque.toString());
		str.append("\n}");
		return str.toString();
	}

	public boolean bind(Pila pila) {
		pila.abreBloque();
		boolean b = dec.bind(pila) && e.bind(pila) && asig.bind(pila) && bloque.bind(pila);
		pila.cierraBloque();
		return b;
	}
}