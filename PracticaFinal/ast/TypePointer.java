package ast;

public class TypePointer extends Type {
	private Type tipo;

	public TypePointer(Type tipo) {
		this.tipoType = KindType.POINTER;
		this.tipo = tipo;
	}

	public String toString() {
		return "pointer " + tipo.toString();
	}

	public Tipo getTipo() {
		if (tipo.kindType() == KindType.REF)
			return tipo.getTipo();
		return tipo;
	}

	public boolean bind(Pila pila) {
		return tipo.bind(pila);
	}

	public boolean isAssignable() {
		return true;
	}
}