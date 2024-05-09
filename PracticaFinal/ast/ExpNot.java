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

    public String generateCode(int depth) {
        StringBuilder ss = new StringBuilder();
        ss.append(super.generateCode(depth));
        ss.append("i32.eqz\n"); // Comparamos el valor con 0, que es lo mismo que hacer not
        return ss.toString();
    }

}
