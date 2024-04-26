package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeFunVoid extends Type {
    private ArrayList<Dec> listaArgs;
    private HashMap<String, Dec> dicArgs;

    public TypeFunVoid(ArrayList<Dec> listaArgs) {
        this.tipoType = KindType.FUNVOID;
        this.listaArgs = listaArgs;
        this.dicArgs = new HashMap<String, Dec>();
        for (Dec dec : listaArgs)
            dicArgs.put(dec.getId(), dec);
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

    public boolean bind(Pila pila) {
        boolean b = true;
        pila.abreBloque();
        for (Dec d : listaArgs) {
            b = d.bind(pila) && b;
        }
        pila.cierraBloque();

        return b;
    }
}