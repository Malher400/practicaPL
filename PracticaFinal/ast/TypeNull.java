package ast;

public class TypeNull extends Tipo {
    public TypeNull() {
        this.consTipo = KindType.NULL;
    }

    public String toString() {
        return "null";
    }

    public void setSize() {
        size = 0;
    }
}