package errors;

import java.util.ArrayList;

public class TypeException extends Exception {
    private ArrayList<TypeException> errors;
    private int fila;
    private int columna;

    public TypeException(int fila, int columna, String message) {
        super(message);
        this.errors = new ArrayList<TypeException>();
        this.fila = fila;
        this.columna = columna;
    }

    public TypeException(ArrayList<TypeException> errors) {
        super();
        this.errors = errors;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public ArrayList<TypeException> getErrors() {
        return errors;
    }

    public void printErrors() {
        for (int i = 0; i < errors.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("FILA ");
            sb.append(errors.get(i).getFila());
            sb.append(", COLUMNA ");
            sb.append(errors.get(i).getColumna());
            sb.append(": ");
            sb.append(errors.get(i).getMessage());
            System.err.println(sb.toString());
        }
    }
}
