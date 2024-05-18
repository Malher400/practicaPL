package ast;

public class DecVar extends Dec {
	public DecVar(int fila, int columna, String id, Type tipo) {
		super(fila, columna, tipo, id);
		this.tipoDec = KindDec.VARIABLE;
	}

	public String toString() { // var lenInt;
		return "    |DecVar| var " + tipo.toString() + " " + id.toString() + ";";
	}

	public boolean bind(Pila pila) {
		pila.insertaId(id, this);
		return tipo.bind(pila);
	}

	public int getSize(){ return tipo.getSize();}

}