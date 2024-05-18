package ast;

import errors.TypeException;

public class TypeIden extends Type {
	private String id;
	private Type tipo;
	private Dec dec;

	public TypeIden(int fila, int columna, String id) {
		this.fila = fila;
		this.columna = columna;
		this.tipoType = KindType.IDEN;
		this.id = id;
	}

	public String toString() {
		return id;
	}

	public Type getTipo() {
		if (tipo.getKindType() == KindType.REF)
			return tipo.getTipo();
		return tipo;
	}

	public Dec getDec() {
		return dec;
	}

	public void setSize() {
		size = tipo.getSize();
	}

	public boolean isAssignable() {
		return tipo.isAssignable();
	}

	public boolean isWritable() {
		return tipo.isWritable();
	}

	public boolean bind(Pila pila) {
		dec = pila.buscaId(id);
		if (dec != null)
			return true;
		else {
			return false;
		}
	}

	public void type() throws TypeException {
		tipo = dec.getTipo();
		setSize();
	}
}
