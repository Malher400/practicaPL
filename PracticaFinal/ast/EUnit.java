package ast;

public abstract class EUnit extends Exp {
	protected Exp opnd;
	
	public EUnit(int fila, int columna, KindExp expTipo, Exp op) {
		this.fila = fila;
	    this.columna = columna;
	    this.tipoExp = expTipo;
	    this.opnd = op;
	}
	
	public Exp getOpnd() {return opnd;}
	
	public String toString() {
		return tipoExp.toString() + '(' + opnd.toString() + ')';
	}

	public boolean bind(Pila pila){
		return opnd.bind(pila);
	}

	public boolean type(){
		return opnd.type();
	}
}
