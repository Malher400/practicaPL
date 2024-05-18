package ast;

import errors.TypeException;

public class TypeRef extends Type {
    private Type tipo;

    public TypeRef(Type tipo) {
        this.tipoType = KindType.REF;
        this.tipo = tipo;
    }

    public String toString() {
        return "|TypeRef| " + tipo.toString();
    }

    public Type getTipo() {
        if (tipo.getKindType() == KindType.REF)
            return tipo.getTipo();
        return tipo;
    }

    public boolean bind(Pila pila) {
        return tipo.bind(pila);
    }

    public void type() throws TypeException {
        tipo.type();
        setSize();
    }

    public boolean isAssignable() {
        return tipo.isAssignable();
    }

    public boolean isWritable() {
        return tipo.isWritable();
    }
}
