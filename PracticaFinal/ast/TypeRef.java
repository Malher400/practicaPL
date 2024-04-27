package ast;

public class TypeRef extends Type {
    private Type tipo;

    public TypeRef(Type tipo) {
        this.tipoType = KindType.REF;
        this.tipo = tipo;
    }

    public String toString() {
        return tipo.toString();
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
        return tipo.isAssignable();
    }

    public boolean isWritable() {
        return tipo.isWritable();
    }
}
