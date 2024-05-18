package ast;

public class TypeNull extends Type {
    public TypeNull() {
        this.tipoType = KindType.NULL;
    }

    public String toString() {
        return "|TypeNull| null";
    }

    public void setSize() {
        size = 0;
    }
}