package ast;

import errors.TypeException;

public class ExpRef extends EUnit {

    public ExpRef(int fila, int columna, Exp opnd) {
        super(fila, columna, KindExp.REF, opnd);
    }

    public String toString() {
        return "  |ExpRef| (#" + opnd.toString() + ')';
    }

    public void type() throws TypeException {
        super.type();
        if (!opnd.getDesignador())
            throw new TypeException(opnd.getFila(), opnd.getColumna(), "El operando no es designable");
        tipo = new TypePointer(opnd.getTipo());
        tipo.type();
    }

    public String generateCode(int depth) {
        return opnd.codeD(depth); // opnd tiene que ser designador
    }

}
