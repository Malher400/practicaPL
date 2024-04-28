package ast;

import errors.TypeException;

public class ExpAnd extends EBin {
   public ExpAnd(int fila, int columna, Exp opnd1, Exp opnd2) {
      super(fila, columna, KindExp.AND, opnd1, opnd2);
   }

   public String toString() {
      return '(' + opnd1.toString() + " && " + opnd2.toString() + ')';
   }

   public void type() throws TypeException {
      super.type();
      if (opnd1.getTipo().kindType() == KindType.BOOL) {
         if (opnd2.getTipo().kindType() == KindType.BOOL) {
            tipo = new TypeBool();
            tipo.type();
         } else
            throw new TypeException(opnd2.getFila(), opnd2.getColumna(), opnd2.toString() + "no es de tipo booleano");
      } else
         throw new TypeException(opnd1.getFila(), opnd1.getColumna(), opnd1.toString() + "no es de tipo booleano");
   }
}
