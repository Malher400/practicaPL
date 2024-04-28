package ast;

import errors.TypeException;

public class ExpDiv extends EBin {

   public ExpDiv(int fila, int columna, Exp opnd1, Exp opnd2) {
      super(fila, columna, KindExp.DIV, opnd1, opnd2);
   }

   public String toString() {
      return '(' + opnd1.toString() + " / " + opnd2.toString() + ')';
   }

   public void type() throws TypeException {
      super.type();
      if (opnd1.getTipo().kindType() == KindType.ENT) {
         if (opnd2.getTipo().kindType() == KindType.ENT) {
            tipo = new TypeEnt();
            tipo.type();
         } else
            throw new TypeException(opnd2.getFila(), opnd2.getColumna(),
                  "El operando " + opnd2.toString() + " no es de tipo entero");
      } else
         throw new TypeException(opnd1.getFila(), opnd1.getColumna(),
               "El operando " + opnd1.toString() + " no es de tipo entero");
   }

}
