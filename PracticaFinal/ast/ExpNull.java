package ast;

import errors.TypeException;

public class ExpNull extends Exp {

   public ExpNull() {
      this.tipoExp = KindExp.NULL;
   }

   public String toString() {
      return "null";
   }

   public void type() throws TypeException {
      tipo = new TypePointer(new TypeNull());
      tipo.type();
      designador = false;
   }

}
