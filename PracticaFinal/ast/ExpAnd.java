package ast;

public class ExpAnd extends EBin {
   public ExpAnd(int fila, int columna, Exp op1, Exp op2) {
      super(fila, columna, KindExp.AND, op1, op2);
   }

   public String toString() {
      return '(' + opnd1.toString() + " && " + opnd2.toString() + ')';
   }

   public boolean type() {
		boolean b = opnd1.type() && opnd2.type();
		if (opnd1.getTipo().kindType() == KindType.ENT && opnd2.getTipo() == KindType.ENT) { 
				tipo = new TipoBooleano();
				tipo.type();
		} else return false;
      return b;
	}

}
