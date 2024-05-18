package ast;

public class DecArg extends DecVar {

	public DecArg(int fila, int columna, String id, Type tipo) {
		super(fila, columna, id, tipo);
	}

	public String toString() {
		if (tipo.getKindType() == KindType.REF)
			return "  |DecArg| " + tipo.toString() + " #" + id.toString();
		else
			return "  DecArg | " + tipo.toString() + ' ' + id.toString();
	}

	

}