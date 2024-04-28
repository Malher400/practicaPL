package ast;

import errors.TypeException;

public class ExpIgual extends EBin {

   public ExpIgual(int fila, int columna, Exp opnd1, Exp opnd2) {
      super(fila, columna, KindExp.IGUAL, opnd1, opnd2);
   }

   public String toString() {
      return '(' + opnd1.toString() + " == " + opnd2.toString() + ')';
   }

   public void type() throws TypeException {
      super.type();
      if (opnd1.getTipo().kindType() == opnd2.getTipo().kindType() && opnd1.getTipo().isAssignable()
            && opnd2.getTipo().isAssignable()) {
         tipo = new TypeBool();
         tipo.type();
      } else
         throw new TypeException(opnd1.getFila(), opnd1.getColumna(), "Los operandos tienen diferente tipo");
   }

}
