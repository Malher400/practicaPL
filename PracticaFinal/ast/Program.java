package ast;

import java.util.ArrayList;

public class Program implements ASTNode {
    private ArrayList<Dec> decs;
    private DecMain main;
    private int fila;
    private int columna;

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Program(int fila, int columna, ArrayList<Dec> decs, DecMain main) {
        this.fila = fila;
        this.columna = columna;
        this.decs = decs;
        this.main = main;
    }

    public NodeKind nodeKind() {
        return NodeKind.PROGRAM;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Dec dec : decs) {
            str.append(dec.toString());
            str.append('\n');
        }
        str.append(main.toString());
        return str.toString();
    }

    public boolean bind(Pila pila){
        pila = new Pila();
        boolean b = true;
        // pila.add(); ???
        for (Dec d : decs){
            b = d.bind(pila) && b;
        }
        b = main.bind(pila) && b;
        // pila.remove(); ???
        return b;
    }

}
