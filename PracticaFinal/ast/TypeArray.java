package ast;

import errors.TypeException;

public class TypeArray extends Type {
	private String tam;
	private Type tipo;

	public TypeArray(Type tipo, String tam) {
		this.tipoType = KindType.ARRAY;
		this.tipo = tipo;
		this.tam = tam;
	}

	public String toString() {
		return "guevArray " + tipo.toString() + " [" + tam + ']';
	}

	public Type getTipo() {
		if (tipo.getKindType() == KindType.REF)
			return tipo.getTipo();
		return tipo;
	}

	public void setSize() {
		size = Integer.parseInt(tam) * tipo.getSize();
	}

	public boolean bind(Pila pila) {
		return tipo.bind(pila);
	}

	public void type() throws TypeException {
		tipo.type();
		setSize();
	}
}