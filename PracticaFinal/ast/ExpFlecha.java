package ast;

public class ExpFlecha extends Exp {
	private Exp id;
	private String campo;

	public ExpFlecha(int fila, int columna, Exp id, String campo) {
		this.fila = fila;
		this.columna = columna;
		this.id = id;
		this.campo = campo;
		this.tipoExp = KindExp.FLECHA;
	}

	public String toString() {
		return id.toString() + "->" + campo;
	}

	public boolean bind(Pila pila) {
		return id.bind(pila);
	}

	public void type() throws TypeException {

		id.type();
	}
}