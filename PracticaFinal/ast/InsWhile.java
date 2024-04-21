package ast;

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

	public boolean bind(){
		return true;
	}
}