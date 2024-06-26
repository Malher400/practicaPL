package ast;

import errors.TypeException;

public class ExpMenor extends EBin {

   public ExpMenor(int fila, int columna, Exp opnd1, Exp opnd2) {
      super(fila, columna, KindExp.MENOR, opnd1, opnd2);
   }

   public String toString() {
      return "  |ExpMenor| " + opnd1.toString() + " < " + opnd2.toString() + ')';
   }

   public void type() throws TypeException {
      super.type();
      if (opnd1.getTipo().getKindType() == KindType.ENT) {
         if (opnd2.getTipo().getKindType() == KindType.ENT) {
            tipo = new TypeBool();
            tipo.type();
         } else
            throw new TypeException(opnd2.getFila(), opnd2.getColumna(),
                  "El operando " + opnd2.toString() + " no es de tipo entero");
      } else
         throw new TypeException(opnd1.getFila(), opnd1.getColumna(),
               "El operando " + opnd1.toString() + " no es de tipo entero");
   }

   public String generateCode(int depth) {
      StringBuilder ss = new StringBuilder();
      ss.append(super.generateCode(depth));
      ss.append("\ti32.lt_s\n");
      return ss.toString();
   }

}
