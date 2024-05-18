package ast;

import errors.TypeException;

public class DecVarIni extends DecVar {
	private Ins asig;

	public DecVarIni(int fila, int columna, String id, Type tipo, Ins asig) {
		super(fila, columna, id, tipo);
		this.asig = asig;
	}

	public String toString() { // var lenInt x := 3
		return "  |DecVarIni| var " + tipo.toString() + " " + asig.toString();
	}

	public boolean bind(Pila pila) {
		pila.insertaId(id, this);
		return tipo.bind(pila) && asig.bind(pila);
	}

	public void type() throws TypeException {
		tipo.type();
		asig.type();
	}

	public String generateCode(int depth){
		return asig.generateCode(depth);
	}
}