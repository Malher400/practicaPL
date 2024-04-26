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
      opnd1.type();
      opnd2.type();
      if (opnd1.getTipo().kindType() == KindType.BOOLEANO && opnd2.getTipo() == KindType.BOOLEANO) {
         tipo = new TipoBooleano();
         tipo.type();
      } else // excepción;
      {
      }

   }

}
