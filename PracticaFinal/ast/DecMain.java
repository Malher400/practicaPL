package ast;

import java.util.ArrayList;

public class DecMain extends DecFunVoid {

    public DecMain(int fila, int columna, String id, Type tipo, ArrayList<Dec> args, Ins bloque) {
        super(fila, columna, id, tipo, args, bloque);
        this.decTipo = KindDec.FUNVOID;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("main ");
        str.append("{\n");
        str.append(bloque.toString());
        str.append("\n}");
        return str.toString();
    }
}