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
        StringBuilder str = new StringBuilder("|ExpFun|");
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

    public String generateCode(int depth) {
        StringBuilder ss = new StringBuilder("\n");
        try {
            for (int i = params.size() - 1; i >= 0; i--) {
                for (int j = 0; j < params.get(i).getTipo().getSize(); j = j + 4) {
                    ss.append("\ti32.const ");
                    ss.append(id.getTipo().getDec(i).getDelta());
                    ss.append("\n");
                    ss.append("\tget_global $SP\n");
                    ss.append("\ti32.add\n");
                    if (id.getTipo().getDec(i).getTipo().getKindType() != KindType.REF) {
                        if (params.get(i).getDesignador()) {
                            ss.append(params.get(i).codeD(depth));
                            ss.append("\ti32.load offset=");
                            ss.append(j);
                            ss.append("\n");
                        } else {
                            ss.append(params.get(i).generateCode(depth));
                        }
                        ss.append("\ti32.store offset=");
                        ss.append(j);
                        ss.append("\n");
                    } else {
                        ss.append(params.get(i).codeD(depth));
                        ss.append("\ti32.store\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error en ExpFun: " + e);
        }

        ss.append("\tget_global $SP\n"); // Reserva de espacio en la pila para la llamada
        ss.append("\ti32.const ");
        ss.append(id.getTipo().getSize());
        ss.append("\n");
        ss.append("\ti32.const 12\n");
        ss.append("\ti32.add\n");
        ss.append("\tcall $reserveStack\n");
        ss.append("\ti32.store\n");

        ss.append("\tget_global $MP\n"); // Guardamos el SP
        ss.append("\tget_global $MP\n");
        ss.append("\ti32.load\n");
        ss.append("\ti32.store offset=4\n");
        ss.append("\tget_global $MP\n");
        ss.append("\tget_global $SP\n");
        ss.append("\ti32.store offset=8\n");

        ss.append("\tcall $"); // Llamada a la funcion
        ss.append(id.getTipo().getId());
        ss.append("\n");
        ss.append("\tcall $freeStack\n");
        return ss.toString();
    }
}
