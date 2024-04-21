package ast;

public class ExpIden extends Exp {

   String id;

   public ExpIden(int fila, int columna, String iden) {
      this.fila = fila;
      this.columna = columna;
      this.id = iden;
      this.tipoExp = KindExp.IDEN;
   }

   public String toString() {
      return id;
   }

   // bind?

}
