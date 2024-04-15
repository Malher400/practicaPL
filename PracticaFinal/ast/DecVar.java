package ast;

public class DecVar extends Dec {
	public DecVar(int fila, int columna, String id, Type tipo) {
		super(fila, columna, tipo, id);
		this.tipoDec = KindDec.VARIABLE;
	}

	public String toString() {
		return "var " + tipo.toString() + " " + id.toString() + ";";
	}
}