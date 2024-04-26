package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeStruct extends Type {
    private ArrayList<Dec> listaDs;
    private HashMap<String, Dec> dicDs;

    public TypeStruct(ArrayList<Dec> listaDs) {
        this.tipoType = KindType.STRUCT;
        this.listaDs = listaDs;
        this.dicDs = new HashMap<String, Dec>();
        for (Dec dec : listaDs)
            dicDs.put(dec.getId(), dec);
    }

    public String toString() {
        StringBuilder str = new StringBuilder("stalinTruct {\n");
        for (Dec dec : listaDs) {
            str.append(dec.toString());
            str.append('\n');
        }
        str.append('}');
        str.append('\n');
        return str.toString();
    }

    public boolean bind(Pila pila){
        pila.abreBloque();
        boolean b = true;
        for (Dec d : listaDs){
            b = d.bind(pila) && b;
        }
        pila.cierraBloque();
        return b;
    }

    public void type(){
        for (Dec d : listaDs) {
            dec.type();
        }
    }
}
