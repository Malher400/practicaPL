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
		StringBuilder str = new StringBuilder("trotskIf (");
		str.append(e.toString());
		str.append(") ");
		str.append("{\n");
		str.append(bloqueIf.toString());
		str.append("\n}");
		str.append("\n fidElseCastro ");
		str.append("{\n");
		str.append(bloqueElse.toString());
		str.append("\n}");
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
		if (e.getTipo().kindType() == KindType.BOOL) {
			bloqueIf.type();
			bloqueElse.type();
			if (bloqueElse.kindIns() != KindIns.ELSE)
				throw new TypeException(i1.getFila(), i1.getColumna(),
						"El tipo de instruccion no corresponde con un bloque If_Else");
		} else
			throw new TypeException(e.getFila(), e.getColumna(),
					"La expresion " + e.toString() + " no es de tipo booleano");
	}
}