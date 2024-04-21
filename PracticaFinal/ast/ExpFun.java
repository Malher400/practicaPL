package ast;

import java.util.ArrayList;

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

    public boolean bind(Pila pila){
        boolean b = id.bind(pila);
        for (Exp p : params){
            b = p.bind(pila) && b;
        }
        return b;
    }
}
