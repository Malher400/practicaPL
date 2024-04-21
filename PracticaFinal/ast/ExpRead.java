package ast;

public class ExpRead extends Exp {
    private Type tipoLect;

    public ExpRead(int fila, int columna, Type tipoLect) {
        this.fila = fila;
        this.columna = columna;
        this.tipoLect = tipoLect;
        this.tipoExp = KindExp.READ;
    }

    public String toString() {
        return "red" + tipoLect.toString();
    }

    public boolean bind(Pila pila){
        return tipoLect.bind(pila);
    }
}