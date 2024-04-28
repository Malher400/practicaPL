package ast;

import java.util.ArrayList;
import java.util.HashMap;
import errors.TypeException;

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

    public void setSize() {
        size = 0;
        for (Dec dec : listaDs) {
            size += dec.getTipo().getSize();
        }
    }

    public boolean bind(Pila pila) {
        pila.abreBloque();
        boolean b = true;
        for (Dec d : listaDs) {
            b = d.bind(pila) && b;
        }
        pila.cierraBloque();
        return b;
    }

    public Dec getDec(String iden) throws TypeException {
        return dicDs.get(iden);
    }

    public void type() throws TypeException {
        ArrayList<TypeException> errores = new ArrayList<TypeException>();
        for (Dec dec : listaDs) {
            try {
                dec.type();
            } catch (TypeException te) {
                for (TypeException tEx : te.getErrors()) {
                    errores.add(tEx);
                }
                if (te.getErrors().size() == 0)
                    errores.add(te);
            }
        }
        if (errores.size() != 0)
            throw new TypeException(errores);
        setSize();
    }
}
