package errors;

import java.util.ArrayList;

public class TypeException extends Exception {
    private int fila;
    private int columna;
    private ArrayList<TypeException> exceptions;

    public TypeException(int fila, int columna) {
        super();
        this.fila = fila;
        this.columna = columna;
        this.exceptions = new ArrayList<TypeException>();
    }

    public TypeException(int fila, int columna, ArrayList<TypeException> exceptions) {
        super();
        this.fila = fila;
        this.columna = columna;
        this.exceptions = exceptions;
    }
}
