package ast;

import errors.TypeException;

public class ExpPunto extends Exp {
	private Exp id;
	private String campo;

	public ExpPunto(int fila, int columna, Exp id, String campo) {
		this.fila = fila;
		this.columna = columna;
		this.id = id;
		this.campo = campo;
		this.tipoExp = KindExp.PUNTO;
	}

	public String toString() {
		return id.toString() + '.' + campo;
	}

	public boolean bind(Pila pila) {
		return id.bind(pila);
	}

	public void type() throws TypeException {
		id.type();
		if (id.getTipo().kindType() == KindType.IDEN) {
			if (id.getTipo().getTipo().kindType() == KindType.STRUCT) {
				if (id.getDec(campo) != null) {
					tipo = id.getDec(campo).getTipo();
					designador = id.getDesignador();
				} else
					throw new TypeException(fila, columna, "No existe un campo " + campo + " dentro de este struct");
			} else
				throw new TypeException(fila, columna, "La expresion " + id.toString() + " no es de tipo struct");
		} else
			throw new TypeException(fila, columna,
					"La expresion " + id.toString() + " no es de tipo iden");
	}

}