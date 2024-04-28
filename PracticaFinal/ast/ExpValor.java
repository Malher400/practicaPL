
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
      if (opnd.getTipo().kindType() != KindType.PUNTERO)
         throw new TypeException(opnd.getFila(), opnd.getColumna(),
               "El operando " + opnd.toString() + " no es de tipo puntero");
      tipo = opnd.getTipo().getTipo();
   }

}
