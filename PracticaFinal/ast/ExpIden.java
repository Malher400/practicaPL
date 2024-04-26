package ast;

public class ExpIden extends Exp {

   private String id;
   private Dec dec;

   public ExpIden(int fila, int columna, String iden) {
      this.fila = fila;
      this.columna = columna;
      this.id = iden;
      this.tipoExp = KindExp.IDEN;
   }

   public String toString() {
      return id;
   }

   public boolean bind(Pila pila) {
      dec = pila.buscaId(id);
      if (dec.equals(null))
         return false;
      else
         return true;
   }

}
