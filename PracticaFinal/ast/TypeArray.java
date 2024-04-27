package ast;

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

	public Tipo getTipo() {
		if (tipo.kindType() == KindType.REF)
			return tipo.getTipo();
		return tipo;
	}

	public boolean bind(Pila pila) {
		return tipo.bind(pila) && tam.bind(pila);
	}
}