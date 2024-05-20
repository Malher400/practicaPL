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
      return "|ExpIden| " + id;
   }

   public boolean bind(Pila pila) {
      dec = pila.buscaId(id);
      //System.out.println(id + " / " + dec + " // ");
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
      StringBuilder ss = new StringBuilder("\ti32.const " + dec.getDelta() + '\n');


      if (dec.getDepth() == 1){
         ss.append("\tglobal.get $MP\n");
         ss.append("\ti32.add\n");
      }
      if (dec.getTipo().getKindType() == KindType.REF) {
         ss.append("\ti32.load\n");
      }

      return ss.toString();
   }

   public String generateCode(int depth) {
      StringBuilder ss = new StringBuilder();
      ss.append(codeD(depth));
      ss.append("\ti32.load \n");
      return ss.toString();
   }

}
