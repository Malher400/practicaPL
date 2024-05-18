package ast;

public class TypeBool extends Type {
	public TypeBool() {
		this.tipoType = KindType.BOOL;
	}

	public String toString() {
		return "|TypeBool| boolShevik";
	}

	public boolean isAssignable() {
		return true;
	}

	public boolean isWritable() {
		return true;
	}
}