package ast;

public class TypeEnt extends Type {
	public TypeEnt() {
		this.tipoType = KindType.ENT;
	}
	
	public String toString() {return "lenInt";}

	public boolean bind(Pila pila){return true;}
}