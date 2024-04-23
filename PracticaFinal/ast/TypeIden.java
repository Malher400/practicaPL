package ast;

public class TypeIden extends Type {
	private String id;

	public TypeIden(int fila, int columna, String id) {
		this.fila = fila;
		this.columna = columna;
		this.tipoType = KindType.IDEN;
		this.id = id;
	}

	public String toString() {
		return id;
	}

	public boolean bind(PilaSimbolos pila) {
		if (pila.buscaId(id) != null)
			return false;
		else
			return true;
	}
}
