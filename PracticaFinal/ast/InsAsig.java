package ast;

import errors.TypeException;

public class InsAsig extends Ins {
	private Exp e1, e2;
	private boolean isDec;

	public InsAsig(int fila, int columna, Exp e1, Exp e2, boolean isDec) {
		this.fila = fila;
		this.columna = columna;
		this.tipoIns = KindIns.ASIG;
		this.e1 = e1;
		this.e2 = e2;
		this.isDec = isDec;
	}

	public String toString() {
		return "    |InsAsig| " + e1.toString() + " := " + e2.toString() + ";";
	}

	public boolean bind(Pila pila) {
		return e1.bind(pila) && e2.bind(pila);
	}

	public void type() throws TypeException {
		e1.type();
		if (!isDec && !e1.getDesignador())
			throw new TypeException(e1.getFila(), e1.getColumna(),
					"El operando " + e1.toString() + " no es un designador");
		if (!e1.getTipo().isAssignable())
			throw new TypeException(e1.getFila(), e1.getColumna(),
					"El operando " + e1.toString() + " no es un tipo asignable");
		e2.type();
		if (e1.getTipo().getKindType() == KindType.IDEN) {
			if (!e1.getTipo().getTipo().equals(e2.getTipo()))
				throw new TypeException(fila, columna, "Los operandos no tienen el mismo tipo");
		} else {
			if (!e1.getTipo().equals(e2.getTipo()))
				throw new TypeException(fila, columna, "Los operandos no tienen el mismo tipo");
		}
	}

	public String generateCode(int depth) {
		StringBuilder sb = new StringBuilder();
		sb.append(e1.codeD(depth));
		sb.append(e2.generateCode(depth));
		sb.append("\ti32.store\n");
		return sb.toString();
	}
}