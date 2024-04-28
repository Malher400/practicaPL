package ast;

import errors.TypeException;

public class DecVarConst extends DecVar {
	private Ins asig;

	public DecVarConst(int fila, int columna, String id, Type tipo, Ins asig) {
		super(fila, columna, id, tipo);
		this.tipoDec = KindDec.CONSTANTE;
		this.asig = asig;
	}

	public String toString() { // var const lenInt N := 20
		return "var const " + tipo.toString() + " " + asig.toString();
	}

	public boolean bind(Pila pila) {
		pila.insertaId(id, this);
		return tipo.bind(pila) && asig.bind(pila);
	}

	public void type() throws TypeException {
		tipo.type();
		asig.type();
	}
}
