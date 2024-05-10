
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

   public String codeD(int depth) { // Por si se accede a la referencia de una variable
      StringBuilder ss = new StringBuilder();
      ss.append(opnd.codeD(depth));
      ss.append("i32.load\n");
      return ss.toString();
   }

   public String generateCode(int depth) {
      StringBuilder ss = new StringBuilder();
      ss.append(super.generateCode(depth));
      ss.append("i32.load\n"); // Carga valor al que apunta la direccion de variable
      return ss.toString();
   }

}
