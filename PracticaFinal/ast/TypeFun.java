package ast;

import java.util.ArrayList;
import java.util.HashMap;
import errors.TypeException;

public class TypeFun extends Type {
    protected ArrayList<Dec> listaArgs;
    protected HashMap<String, Dec> mapArgs;
    protected Type tipo;
    protected String id;

    public TypeFun(Type tipo, ArrayList<Dec> listaArgs, String id) {
        this.tipoType = KindType.FUN;
        this.tipo = tipo;
        this.listaArgs = listaArgs;
        this.id = id;
        this.mapArgs = new HashMap<String, Dec>();
        for (Dec d : listaArgs)
            mapArgs.put(d.getId(), d);
    }

    public String toString() {
        StringBuilder str = new StringBuilder("|TypeFun|");
        if (listaArgs.size() == 0)
            str.append("emptySet");
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
        if (tipo.getKindType() == KindType.REF)
            return tipo.getTipo();
        return tipo;
    }

    public String getId() {
        return id;
    }

    public Dec getDec(int i) throws TypeException {
        return listaArgs.get(i);
    }

    public Dec getDec(String iden) throws TypeException {
        return mapArgs.get(iden);
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