package ast;

import errors.TypeException;

public class ExpNew extends Exp {
	private Type tipoRes;

	public ExpNew(int fila, int columna, Type tipoRes) {
		this.fila = fila;
		this.columna = columna;
		this.tipoRes = tipoRes;
		this.tipoExp = KindExp.NEW;
	}

	public String toString() {
		return "  |ExpNew| new" + tipoRes.toString();
	}

	public void type() throws TypeException {
		tipoRes.type();
		tipo = new TypePointer(tipoRes);
		tipo.type();
		designador = false;
	}

	public String generateCode(int depth) {
		StringBuilder sb = new StringBuilder();
		sb.append("\ti32.const ");
		sb.append(tipoRes.getSize());
		sb.append("\n");
		sb.append("\tcall $reserveHeap\n");
		return sb.toString();
	}
}