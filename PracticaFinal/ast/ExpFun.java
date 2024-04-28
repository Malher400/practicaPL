package ast;

import java.util.ArrayList;
import errors.TypeException;

public class ExpFun extends Exp {
    Exp id;
    ArrayList<Exp> params;

    public ExpFun(int fila, int columna, Exp id, ArrayList<Exp> params) {
        this.fila = fila;
        this.columna = columna;
        this.id = id;
        this.params = params;
        this.tipoExp = KindExp.FUNCION;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(id.toString());
        str.append('(');
        for (Exp e : params) {
            str.append(e.toString());
            str.append(", ");
        }
        str.delete(str.length() - 2, str.length());
        str.append(")");

        return str.toString();
    }

    public boolean bind(Pila pila) {
        boolean b = id.bind(pila);
        for (Exp p : params) {
            b = p.bind(pila) && b;
        }
        return b;
    }

    public void type() throws TypeException {
        id.type();
        if (id.getTipo().getKindType() != KindType.FUN)
            throw new TypeException(fila, columna, "La expresion " + id.toString() + " no es de tipo función");
        for (int i = 0; i < params.size(); ++i) {
            params.get(i).type();
            if (id.getTipo().getDec(i).getTipo().getKindType() == KindType.REF) {
                if (!params.get(i).getDesignador())
                    throw new TypeException(params.get(i).getFila(), params.get(i).getColumna(),
                            "El parametro " + params.get(i).toString() + " no es un designador.");
                if (!params.get(i).getTipo().equals(id.getTipo().getDec(i).getTipo().getTipo()))
                    throw new TypeException(params.get(i).getFila(), params.get(i).getColumna(),
                            "El parametro " + params.get(i).toString() + " no coincide con el tipo declarado.");
            } else {
                if (!params.get(i).getTipo().equals(id.getTipo().getDec(i).getTipo()))
                    throw new TypeException(params.get(i).getFila(), params.get(i).getColumna(),
                            "El parametro " + params.get(i).toString() + " no coincide con el tipo declarado.");
            }
        }
        tipo = id.getTipo().getTipo();
        designador = false;
    }
}
