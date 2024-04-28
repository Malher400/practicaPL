package ast;

import errors.TypeException;

public class ExpEntero extends Exp {

   String id;

   public ExpEntero(int fila, int columna, String e) {
      this.fila = fila;
      this.columna = columna;
      this.id = e;
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

}
