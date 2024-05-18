package ast;

import errors.TypeException;

public class ExpRead extends Exp {

    public ExpRead(int fila, int columna, Type tipo) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
        this.tipoExp = KindExp.READ;
    }

    public String toString() {
        return "red" + tipo.toString();
    }

    public boolean bind(Pila pila) {
        return tipo.bind(pila);
    }

    public void type() throws TypeException {
        tipo.type();
        if (!tipo.isWritable())
            throw new TypeException(fila, columna, "El tipo " + tipo.toString() + " no es legible");
        designador = false;
    }

    public String generateCode(int depth) {
        return "\tcall $read\n";
    }
}