package ast;

import errors.TypeException;

public class InsWrite extends Ins {
	private Exp e;

	public InsWrite(int fila, int columna, Exp e) {
		this.fila = fila;
		this.columna = columna;
		this.e = e;
		this.tipoIns = KindIns.WRITE;
	}

	public String toString() {
		return "    |InsWrite| write (" + e.toString() + ");\n";
	}

	public boolean bind(Pila pila) {
		return e.bind(pila);
	}

	public void type() throws TypeException {
		e.type();
		if (!e.getTipo().isWritable())
			throw new TypeException(fila, columna, "El tipo " + e.getTipo().toString() + " no se puede escribir");
	}

	public String generateCode(int depth) {
		StringBuilder sb = new StringBuilder("");
		sb.append(e.generateCode(depth));
		sb.append("\tcall $print\n");
		return sb.toString();
	}

}