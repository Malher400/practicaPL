package ast;

import java.util.ArrayList;
import java.util.HashMap;
import errors.TypeException;

public class TypeFun extends Type {
    private ArrayList<Dec> listaArgs;
    private Type tipo;

    public TypeFun(Type tipo, ArrayList<Dec> listaArgs) {
        this.tipoType = KindType.FUN;
        this.tipo = tipo;
        this.listaArgs = listaArgs;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (Dec dec : listaArgs) {
            str.append(dec.getTipo().toString());
            str.append(" x ");
        }
        str.delete(str.length() - 3, str.length());
        str.append(" -> ");
        str.append(tipo.toString());
        str.append('\n');
        return str.toString();
    }

    public Type getTipo() {
        if (tipo.kindType() == KindType.REF)
            return tipo.getTipo();
        return tipo;
    }

    public Dec getDec(int i) throws TypeException {
        return listaArgs.get(i);
    }

    public void setSize() {
        size = 0;
    }

    public boolean bind(Pila pila) {
        boolean b = tipo.bind(pila);
        pila.abreBloque();
        for (Dec d : listaArgs) {
            b = d.bind(pila) && b;
        }
        pila.cierraBloque();

        return b;
    }

    public void type() throws TypeException {
        tipo.type();
        for (Dec dec : listaArgs)
            dec.type();
        setSize();
    }
}