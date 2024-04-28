package ast;

import errors.TypeException;

public class ExpNot extends EUnit {

    public ExpNot(int fila, int columna, Exp opnd) {
        super(fila, columna, KindExp.NOT, opnd);
    }

    public String toString() {
        return "(!" + opnd.toString() + ')';
    }

    public void type() throws TypeException {
        super.type();
        if (opnd.getTipo().getKindType() == KindType.BOOL) {
            tipo = new TypeBool();
            tipo.type();
        } else
            throw new TypeException(opnd.getFila(), opnd.getColumna(),
                    "El operando " + opnd.toString() + " no es de tipo booleano");
    }

}
