package ast;

import errors.TypeException;

public class ExpNeg extends EUnit {

   public ExpNeg(int fila, int columna, Exp opnd) {
      super(fila, columna, KindExp.NEG, opnd);
   }

   public String toString() {
      return "(-" + opnd.toString() + ')';
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

   public String generateCode(int depth) {
      StringBuilder ss = new StringBuilder();
      ss.append("i32.const 0\n"); // Hacemos la resta 0 - n, que ees -n
      ss.append(super.generateCode(depth));
      ss.append("i32.sub\n");
      return ss.toString();
   }
}
