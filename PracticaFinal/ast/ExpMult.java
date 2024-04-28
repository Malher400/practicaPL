package ast;

import errors.TypeException;

public class ExpMult extends EBin {

   public ExpMult(int fila, int columna, Exp opnd1, Exp opnd2) {
      super(fila, columna, KindExp.MULT, opnd1, opnd2);
   }

   public String toString() {
      return '(' + opnd1.toString() + " * " + opnd2.toString() + ')';
   }

   public void type() throws TypeException {
      super.type();
      if (opnd1.getTipo().getKindType() == KindType.ENT) {
         if (opnd2.getTipo().getKindType() == KindType.ENT) {
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
