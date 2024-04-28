package ast;

import errors.TypeException;

public class ExpBooleano extends Exp {

   String id;

   public ExpBooleano(int fila, int columna, String b) {
      this.fila = fila;
      this.columna = columna;
      this.id = b;
      this.tipoExp = KindExp.BOOLEANO;
   }

   public String toString() {
      return id;
   }

   public void type() throws TypeException {
      tipo = new TypeBool();
      tipo.type();
      designador = false;
   }

}
