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
}
