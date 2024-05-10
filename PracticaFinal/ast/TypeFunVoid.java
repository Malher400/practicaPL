package ast;

import java.util.ArrayList;
import errors.TypeException;

public class TypeFunVoid extends TypeFun {

    public TypeFunVoid(ArrayList<Dec> listaArgs) { // Mejor considerar las void directamente tipo FUN
        super(new TypeNull(), listaArgs);
    }

    public String toString() {
        StringBuilder str = new StringBuilder("");
        for (Dec dec : listaArgs) {
            str.append(dec.getTipo().toString());
            str.append(" x ");
        }
        str.delete(str.length() - 3, str.length());
        str.append(" -> emptySet");
        str.append('\n');
        return str.toString();
    }

    public void setSize() {
        size = 0;
    }

    public boolean bind(Pila pila) {
        boolean b = true;
        pila.abreBloque();
        for (Dec d : listaArgs) {
            b = d.bind(pila) && b;
        }
        pila.cierraBloque();
        return b;
    }

    public void type() throws TypeException {
        for (Dec dec : listaArgs)
            dec.type();
        setSize();
    }
}