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
      if (dec == null)
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

   public String codeD(int depth) {
      StringBuilder ss = new StringBuilder();
      ss.append("i32.const " + dec.setDelta() + "\n");
      if (dec.getDepth() != 0 && dec.getDepth() >= depth) {
         ss.append("get_local $localStart\n");
         ss.append("i32.add\n");
      } else if (dec.getDepth() == 1 && depth == 2) {
         ss.append("get_global $CP\n");
         ss.append("i32.add\n");
      }
      // En el else la variable es global asi que no hay que sumarle nada al delta

      if (dec.getTipo().kindType() == KindType.REF) {
         ss.append("i32.load\n");
      }
      return ss.toString();
   }

   public String generateCode(int depth) {
      StringBuilder ss = new StringBuilder();
      ss.append(codeD(depth));
      ss.append("i32.load \n");
      return ss.toString();
   }

}
