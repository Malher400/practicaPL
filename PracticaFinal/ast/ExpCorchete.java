package ast;

import errors.TypeException;

public class ExpCorchete extends EBin {

    public ExpCorchete(int fila, int columna, Exp opnd1, Exp opnd2) {
        super(fila, columna, KindExp.CORCHETE, opnd1, opnd2);
    }

    public String toString() {
        return '(' + opnd1.toString() + '[' + opnd2.toString() + "])";
    }

    public void type() throws TypeException {
        super.type();
        if (opnd1.getTipo().getKindType() == KindType.ARRAY) {
            if (opnd2.getTipo().getKindType() == KindType.ENT) {
                tipo = opnd1.getTipo().getTipo();
                designador = opnd1.getDesignador();
            } else
                throw new TypeException(opnd2.getFila(), opnd2.getColumna(),
                        "La expresion " + opnd2.toString() + " no es de tipo entero");

        } else
            throw new TypeException(opnd1.getFila(), opnd1.getColumna(),
                    "La expresion " + opnd1.toString() + " no es de tipo array");
    }

    public String codeD(int depth) {
        StringBuilder ss = new StringBuilder();
        ss.append(opnd1.codeD(depth));
        ss.append("i32.const " + tipo.getSize() + "\n");
        if (depth > 2)
            ss.append(opnd2.generateCode(depth - 2));
        else
            ss.append(opnd2.generateCode(depth));
        ss.append("i32.mul\n");
        ss.append("i32.add\n");
        return ss.toString();
    }

    public String generateCode(int depth) {
        StringBuilder ss = new StringBuilder(generateCodeAsig(depth));
        ss.append("i32.load\n");
        return ss.toString();
    }
}