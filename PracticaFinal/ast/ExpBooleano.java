package ast;

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

   public void type() {
      tipo = new TypeBool();
      tipo.type();
      designador = false;
   }

}
