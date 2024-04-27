package ast;

import errors.TypeException;

public class ExpFlecha extends Exp {
	private Exp id;
	private String campo;

	public ExpFlecha(int fila, int columna, Exp id, String campo) {
		this.fila = fila;
		this.columna = columna;
		this.id = id;
		this.campo = campo;
		this.tipoExp = KindExp.FLECHA;
	}

	public String toString() {
		return id.toString() + "->" + campo;
	}

	public boolean bind(Pila pila) {
		return id.bind(pila);
	}

	public void type() throws TypeException {
		id.type();
		if (id.getTipo().kindType() == KindType.POINTER) {
			if (id.getTipo().getTipo().kindType() == KindType.IDEN) {
				if (id.getTipo().getTipo().getTipo().kindType() == KindType.STRUCT) {
					tipo = id.getDec(campo).getTipo();
					designador = id.getDesignador();
				} else
					throw new TypeException(fila, columna, "La expresion " + id.toString() + " no es de tipo puntero");
			} else
				throw new TypeException(fila, columna, "La expresion " + id.toString() + " no es de tipo iden");
		} else
			throw new TypeException(fila, columna, "La expresion " + id.toString() + " no es de tipo struct");
	}
}