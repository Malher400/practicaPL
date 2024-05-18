package ast;

public class TypeEnt extends Type {
	public TypeEnt() {
		this.tipoType = KindType.ENT;
	}

	public String toString() {
		return "|TypeEnt| lenInt";
	}

	public boolean isAssignable() {
		return true;
	}

	public boolean isWritable() {
		return true;
	}
}