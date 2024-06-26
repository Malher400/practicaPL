package ast;

import errors.TypeException;

public class InsIf_Else extends Ins {
	private Exp e;
	private Ins bloqueIf;
	private Ins bloqueElse;

	public InsIf_Else(int fila, int columna, Exp e, Ins bloqueIf, Ins bloqueElse) {
		this.fila = fila;
		this.columna = columna;
		this.tipoIns = KindIns.IF_ELSE;
		this.bloqueIf = bloqueIf;
		this.bloqueElse = bloqueElse;
		this.e = e;
	}

	public String toString() {
		StringBuilder str = new StringBuilder("    |InsIf_Else| trotskIf (");
		str.append(e.toString());
		str.append(") ");
		str.append("{\n");
		str.append(bloqueIf.toString());
		str.append("  }");
		str.append("\n  fidElseCastro ");
		str.append("{\n");
		str.append(bloqueElse.toString());
		str.append("  }");
		return str.toString();
	}

	public boolean bind(Pila pila) {
		boolean b = e.bind(pila);

		pila.abreBloque();
		b = b && bloqueIf.bind(pila);
		pila.cierraBloque();

		pila.abreBloque();
		b = b && bloqueElse.bind(pila);
		pila.cierraBloque();

		return b;
	}

	public void type() throws TypeException {
		e.type();
		if (e.getTipo().getKindType() == KindType.BOOL) {
			bloqueIf.type();
			bloqueElse.type();
		} else
			throw new TypeException(e.getFila(), e.getColumna(),
					"La expresion " + e.toString() + " no es de tipo booleano");
	}

	public int setDelta(int d) {
		int i = d;
		i = bloqueIf.setDelta(i);
		i = bloqueElse.setDelta(i);
		return d;
	}

	public void setDepth(int d){
		bloqueIf.setDepth(d);
		bloqueElse.setDepth(d);
	}

	public String generateCode(int depth) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.generateCode(depth));
		sb.append("\tif\n");
		sb.append(bloqueIf.generateCode(depth));
		sb.append("\telse\n");
		sb.append(bloqueElse.generateCode(depth));
		sb.append("\tend\n");
		return sb.toString();
	}
}