
package ast;

import errors.TypeException;

public class ExpValor extends EUnit {

   public ExpValor(int fila, int columna, Exp opnd) {
      super(fila, columna, KindExp.VALOR, opnd);
   }

   public String toString() {
      return "(*" + opnd.toString() + ')';
   }

   public boolean getDesignador() {
      return true;
   }

   public void type() throws TypeException {
      super.type();
      if (opnd.getTipo().getKindType() != KindType.POINTER)
         throw new TypeException(opnd.getFila(), opnd.getColumna(),
               "El operando " + opnd.toString() + " no es de tipo puntero");
      tipo = opnd.getTipo().getTipo();
   }

   public String generateCodeD(int depth) {
      StringBuilder ss = new StringBuilder();
      ss.append(opnd.generateCodeD(depth));
      ss.append("i32.load\n");
      return ss.toString();
   }

   public String generateCode(int depth) {
      StringBuilder ss = new StringBuilder();
      ss.append(super.generateCode(depth));
      ss.append("i32.load\n");
      return ss.toString();
   }

}
