package ast;

import errors.TypeException;

public class TypeArray extends Type {
	private Exp tam;
	private Type tipo;

	public TypeArray(Type tipo, Exp tam) {
		this.tipoType = KindType.ARRAY;
		this.tipo = tipo;
		this.tam = tam;
	}

	public String toString() {
		return "guevArray " + tipo.toString() + " [" + tam.toString() + ']';
	}

	public Type getTipo() {
		if (tipo.getKindType() == KindType.REF)
			return tipo.getTipo();
		return tipo;
	}

	public void setSize() {
		size = Integer.parseInt(tam.toString()) * tipo.getSize();
	}

	public boolean bind(Pila pila) {
		return tipo.bind(pila) && tam.bind(pila);
	}

	public void type() throws TypeException {
		tipo.type();
		setSize();
	}
}