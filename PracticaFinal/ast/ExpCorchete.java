package ast;

import errors.TypeException;

public class ExpCorchete extends EBin {

    public ExpCorchete(int fila, int columna, Exp op1, Exp op2) {
        super(fila, columna, KindExp.CORCHETE, op1, op2);
    }

    public String toString() {
        return '(' + opnd1.toString() + '[' + opnd2.toString() + "])";
    }

    public void type() throws TypeException {
        super.type();
        if (opnd1.getTipo().kindType() == KindType.ARRAY) {
            if (opnd2.getTipo().kindType() == KindType.ENT) {
                tipo = opnd1.getTipo().getTipo();
                designador = opnd1.getDesignador();
            } else
                throw new TypeException(opnd2.getFila(), opnd2.getColumna(),
                        "La expresion " + opnd2.toString() + " no es de tipo entero");

        } else
            throw new TypeException(opnd1.getFila(), opnd1.getColumna(),
                    "La expresion " + opnd1.toString() + " no es de tipo array");
    }
}