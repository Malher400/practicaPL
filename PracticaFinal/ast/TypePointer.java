package ast;

import errors.TypeException;

public class TypePointer extends Type {
	private Type tipo;

	public TypePointer(Type tipo) {
		this.tipoType = KindType.POINTER;
		this.tipo = tipo;
	}

	public String toString() {
		return "|TypePointer| pointer " + tipo.toString();
	}

	public Type getTipo() {
		if (tipo.getKindType() == KindType.REF)
			return tipo.getTipo();
		return tipo;
	}

	public boolean bind(Pila pila) {
		return tipo.bind(pila);
	}

	public void type() throws TypeException {
		tipo.type();
		setSize();
	}

	public boolean isAssignable() {
		return true;
	}
}