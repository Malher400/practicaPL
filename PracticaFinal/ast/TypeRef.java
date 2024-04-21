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

    public boolean bind(Pila pila){
        return tipo.bind(pila);
    }
}
