package ast;

import errors.TypeException;

public class ExpPos extends EUnit {

   public ExpPos(int fila, int columna, Exp opnd) {
      super(fila, columna, KindExp.POS, opnd);
   }

   public String toString() {
      return "  |ExpPos| (+" + opnd.toString() + ')';
   }

   public void type() throws TypeException {
      super.type();
      if (opnd.getTipo().getKindType() == KindType.ENT) {
         tipo = new TypeEnt();
         tipo.type();
      } else
         throw new TypeException(opnd.getFila(), opnd.getColumna(),
               "El operando " + opnd.toString() + " no es de tipo entero");
   }

}
