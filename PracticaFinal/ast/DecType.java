package ast;

import errors.TypeException;

public class DecType extends Dec {
	public DecType(int fila, int columna, String id, Type tipo) {
		super(fila, columna, tipo, id);
		this.tipoDec = KindDec.TIPO;
	}

	public String toString() {
		return "type " + id + " := " + tipo.toString() + ';';
	}

	public boolean bind(Pila pila) {
		pila.insertaId(id, this);
		boolean b = tipo.bind(pila);

		return b;
	}

	public void type() throws TypeException {
		tipo.type();
	}
}