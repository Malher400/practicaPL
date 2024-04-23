package ast;

public class InsWrite extends Ins {
	private Exp e;

	public InsWrite(int fila, int columna, Exp e) {
		this.fila = fila;
		this.columna = columna;
		this.e = e;
		this.tipoIns = KindIns.WRITE;
	}

	public String toString() {
		return "write (" + e.toString() + ");\n";
	}

	public boolean bind(Pila pila) {
		return e.bind(pila);
	}
}