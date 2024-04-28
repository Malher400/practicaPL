package ast;

import errors.TypeException;

public class ExpIden extends Exp {

   private String id;
   private Dec dec;

   public ExpIden(int fila, int columna, String id) {
      this.fila = fila;
      this.columna = columna;
      this.id = id;
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

   public void type() throws TypeException {
      tipo = dec.getTipo();
      if (dec.getKindDec() == KindDec.VARIABLE)
         designador = true;
      else
         designador = false;
   }

}
