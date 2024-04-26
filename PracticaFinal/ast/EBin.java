package ast;

import errors.TypeException;

public abstract class EBin extends Exp {
   protected Exp opnd1;
   protected Exp opnd2;

   public EBin(int fila, int columna, KindExp expTipo, Exp op1, Exp op2) {
      this.tipoExp = expTipo;
      this.opnd1 = op1;
      this.opnd2 = op2;
      this.fila = fila;
      this.columna = columna;
   }

   public Exp opnd1() {
      return opnd1;
   }

   public Exp opnd2() {
      return opnd2;
   }

   public String toString() {
      return tipoExp.toString() + '(' + opnd1.toString() + ',' + opnd2.toString() + ')';
   }

   public boolean bind(Pila pila) {
      return opnd1.bind(pila) && opnd2.bind(pila);
   }

   public void type() throws TypeException {
      opnd1.type();
      opnd2.type();
      designador = false;
   }

}
