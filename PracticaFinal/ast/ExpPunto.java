
package ast;

public class ExpPunto extends Exp {
	private Exp id;
	private String campo;

	public ExpPunto(int fila, int columna, Exp id, String campo) {
		this.fila = fila;
		this.columna = columna;
		this.id = id;
		this.campo = campo;
		this.tipoExp = KindExp.PUNTO;
	}

	public String toString() {
		return id.toString() + '.' + campo;
	}

	public boolean bind(Pila pila){
		return id.bind(pila);
	}

}