package ast;

import errors.TypeException;

public class ExpEntero extends Exp {

   String id;

   public ExpEntero(int fila, int columna, String id) {
      this.fila = fila;
      this.columna = columna;
      this.id = id;
      this.tipoExp = KindExp.ENTERO;
   }

   public String toString() {
      return id;
   }

   public void type() throws TypeException {
      tipo = new TypeEnt();
      tipo.type();
      designador = false;
   }

   public String generateCode(int depth) {
      return "\ti32.const " + id + "\n";
   }

}
